<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">

    Ext.namespace( 'Man');

    Ext.define('Man.gridModel', {
        extend:'Ext.data.Model',
        fields:[
            {name:'manId',type:'int'}
            ,{name:'fullName',type:'string'}
            ,{name:'birthDay',type:'string'}
            ,{name:'pasport',type:'string'}
            ,{name:'pasportId',type:'int'}
            ,{name:'seriesDoc',type:'int'}
            ,{name:'numDoc',type:'int'}
        ]
    });

    Man.loadStore = function() {
        var form = Ext.getCmp('Man.searchForm');
        var store = Ext.StoreManager.lookup('Store.man.grid');
        store.proxy.extraParams = form.getValues()
        store.load();
    };


    Man.searchWindow = function (mask) {
        var window = Ext.create('Ext.window.Window', {
            title: 'Выбор клиентов'
            ,height: 300
            ,width: 650
            ,layout: 'fit'
            ,closable:false
            ,items:[
                {
                    xtype:'fieldset'
                    , border: 0
                    , layout: 'vbox'
                    , width: '100%'
                    , items: [
                        {
                            xtype: 'form'
                            , id:'Man.searchForm'
                            , border: 0
                            , width: '100%'
                            , layout: 'hbox'
                            , items: [
                                {
                                    xtype: 'textfield'
                                    , itemId: 'lastName'
                                    , name: 'lastName'
                                    , labelWidth: 60
                                    , width:'30%'
                                    , fieldLabel: 'Фамилия'
                                    , margin: '10 20 10 20'
                                    , maskRe:/[А-яЁё]/
                                    , listeners: {
                                        change: function (field, newValue, oldValue, eOpts) {
                                            makeNameString(newValue)
                                        }
                                    }
                                }
                                ,{
                                    xtype: 'textfield'
                                    , itemId: 'firstName'
                                    , name: 'firstName'
                                    , labelWidth: 40
                                    , width:'30%'
                                    , fieldLabel: 'Имя'
                                    , margin: '10 20 10 0'
                                    , maskRe:/[А-яЁё]/
                                    , listeners: {
                                        change: function (field, newValue, oldValue, eOpts) {
                                            makeNameString(newValue)
                                        }
                                    }
                                }
                                ,{
                                    xtype: 'textfield'
                                    , itemId: 'secondName'
                                    , name: 'secondName'
                                    , labelWidth: 60
                                    , width:'30%'
                                    , fieldLabel: 'Отчество'
                                    , margin: '10 20 10 0'
                                    , maskRe:/[А-яЁё]/
                                    , listeners: {
                                        change: function (field, newValue, oldValue, eOpts) {
                                            makeNameString(newValue)
                                        }
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    , cls : 'lp-button-image'
                                    , tooltip : 'Поиск'
                                    , margin: '10 20 10 20'
                                    , handler: function (btn) {
                                        Man.loadStore();
                                    }
                                }
                            ]
                        }
                        ,{
                            xtype: 'grid'
                            , width: '100%'
                            ,id: 'Man.searchManGrid'
                            ,store: {
                                autoLoad: false
                                ,storeId:'Store.man.grid'
                                ,model:'Man.gridModel'
                                ,proxy:{
                                    type:'ajax'
                                    ,url:'<spring:url value="/page/man/search"/>'
                                    ,reader:{
                                        type:'json'
                                        ,root:'result'
                                    }
                                }
                            }
                            ,columns:{
                                items:[
                                    {
                                        text:'ФИО'
                                        ,dataIndex:'fullName'
                                        ,flex:1
                                    }
                                    ,{
                                        text:'Дата рождения'
                                        ,dataIndex:'birthDay'
                                        ,flex:1
                                    }
                                    ,{
                                        text:'Паспортные данные'
                                        ,dataIndex:'pasport'
                                        ,flex:1
                                    }
                                ]
                            }
                            ,listeners:{
                                itemdblclick:function( grid, record, item, index, e, eOpts ){
                                    var form = Ext.getCmp('ContractForm');
                                    form.getForm().setValues(record.data);
                                    window.close();
                                    mask.hide();
                                }
                            }
                            ,minHeight:150
                        }
                        , {
                            xtype: 'fieldset'
                            , border: 0
                            , margin: '20 0 30 0'
                            , width: '100%'
                            , layout: {
                                type: 'vbox',
                                align: 'center'
                            }
                            , items: [
                                {
                                    xtype: 'fieldset'
                                    , border: 0
                                    , layout:'hbox'
                                    , items: [
                                        {
                                            xtype: 'button'
                                            ,text : 'Выбрать'
                                            , margin: '0 20 0 0'
                                            ,handler:function (btn) {
                                                var grid = Ext.getCmp('Man.searchManGrid');
                                                var selection = grid.getSelectionModel().getSelection()[0];
                                                if(selection){
                                                    var form = Ext.getCmp('ContractForm');
                                                    form.getForm().setValues(selection.data);
                                                    window.close();
                                                    mask.hide();
                                                }
                                                else{
                                                   //TODO
                                                }
                                            }
                                        }
                                        ,{
                                            xtype: 'button'
                                            ,text : 'Новый'
                                            , margin: '0 20 0 0'
                                            ,handler:function (btn) {
                                                var mask = new Ext.LoadMask(window, {msg: "Новый клиент..."});
                                                mask.show();
                                                Man.createWindow(mask);
                                            }
                                        }
                                        ,{
                                            xtype: 'button'
                                            ,text : 'Закрыть'
                                            ,handler:function (btn) {
                                                window.close();
                                                mask.hide();
                                            }
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        });
        window.show();
    };

    Man.createWindow = function (mask) {
        var windowCreate = Ext.create('Ext.window.Window', {
            title: 'Новый клиент'
            ,height: 300
            ,width: 650
            ,layout: 'fit'
            ,closable:false
            ,items:[
                {
                    xtype: 'form'
                    , id:'Man.createManForm'
                    , border: 0
                    , layout: 'vbox'
                    , width: '100%'
                    , items: [
                        {
                            xtype: 'fieldset'
                            , border: 0
                            , width: '100%'
                            , layout: 'hbox'
                            , items: [
                                {
                                    xtype: 'textfield'
                                    , itemId: 'lastName'
                                    , name: 'lastName'
                                    , labelWidth: 60
                                    , fieldLabel: 'Фамилия'
                                    , width: '33%'
                                    , margin: '10 10 10 10'
                                    , maskRe: /[А-яЁё]/
                                    , allowBlank :false
                                    , listeners: {
                                        change: function (field, newValue, oldValue, eOpts) {
                                            makeNameString(newValue)
                                        }
                                    }
                                }
                                , {
                                    xtype: 'textfield'
                                    , itemId: 'firstName'
                                    , name: 'firstName'
                                    , labelWidth: 40
                                    , fieldLabel: 'Имя'
                                    , width: '33%'
                                    , margin: '10 10 10 0'
                                    , maskRe: /[А-яЁё]/
                                    , allowBlank :false
                                    , listeners: {
                                        change: function (field, newValue, oldValue, eOpts) {
                                            makeNameString(newValue)
                                        }
                                    }
                                }
                                , {
                                    xtype: 'textfield'
                                    , itemId: 'secondName'
                                    , name: 'secondName'
                                    , labelWidth: 60
                                    , fieldLabel: 'Отчество'
                                    , width: '33%'
                                    , margin: '10 10 10 0'
                                    , maskRe: /[А-яЁё]/
                                    , listeners: {
                                        change: function (field, newValue, oldValue, eOpts) {
                                            makeNameString(newValue)
                                        }
                                    }
                                }
                            ]
                        }
                        ,{
                            xtype: 'fieldset'
                            , border: 0
                            , width: '100%'
                            , layout: 'hbox'
                            , items: [
                                {
                                    xtype: 'datefield'
                                    , itemId: 'birthDay'
                                    , name: 'birthDay'
                                    , width: '40%'
                                    , fieldLabel: 'Дата рождения'
                                    , labelWidth: 100
                                    , allowBlank: false
                                    , margin: '10 20 10 10'
                                }
                            ]
                        }
                        ,{
                            xtype: 'fieldset'
                            , border: 0
                            , margin: '20 0 30 0'
                            , width: '100%'
                            , layout: {
                                type: 'vbox',
                                align: 'center'
                            }
                            , items: [
                                {
                                    xtype: 'fieldset'
                                    , border: 0
                                    , layout:'hbox'
                                    , items: [
                                        {
                                            xtype: 'button'
                                            ,text : 'Сохранить'
                                            , margin: '0 20 0 0'
                                            ,handler:function (btn) {
                                                var form = Ext.getCmp('Man.createManForm');
                                                if(form.isValid()) {
                                                    Ext.Ajax.request({
                                                        url: '<spring:url value="/page/man/create"/>'
                                                        , params:form.getValues()
                                                        , method: 'POST'
                                                        , success: function (resp, opts) {
                                                            if(Ext.decode(resp.responseText)) {
                                                                Man.loadStore();
                                                                windowCreate.close();
                                                                mask.hide();
                                                            }
                                                        }
                                                        , failure: function (resp, opts) {
                                                            alert("Ошибка")
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                        ,{
                                            xtype: 'button'
                                            ,text : 'Отменить'
                                            ,handler:function (btn) {
                                                windowCreate.close();
                                                mask.hide();
                                            }
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        });
        windowCreate.show();
    };

    Man.changeWindow = function (mask,manId) {
        Ext.Ajax.request({
            url: '<spring:url value="/page/man/getMan"/>'
            , params:{
                manId:manId
            }
            , method: 'GET'
            , success: function (resp, opts) {
                var result = Ext.decode(resp.responseText);
                var form = Ext.getCmp('Man.changeManForm');
                form.getForm().setValues(result);
                windowChange.show();
                mask.hide();
            }
            , failure: function (resp, opts) {
                alert("Ошибка")
            }
        });
        var windowChange = Ext.create('Ext.window.Window', {
            title: 'Клиент'
            ,height: 300
            ,width: 650
            ,layout: 'fit'
            ,closable:false
            ,items:[
                {
                    xtype: 'form'
                    , id:'Man.changeManForm'
                    , border: 0
                    , layout: 'vbox'
                    , width: '100%'
                    , items: [
                    {
                        xtype: 'fieldset'
                        , border: 0
                        , width: '100%'
                        , layout: 'hbox'
                        , items: [
                            {
                                xtype: 'textfield'
                                , itemId: 'lastName'
                                , name: 'lastName'
                                , labelWidth: 60
                                , fieldLabel: 'Фамилия'
                                , width: '33%'
                                , margin: '10 10 10 10'
                                , maskRe: /[А-яЁё]/
                                , allowBlank :false
                                , listeners: {
                                change: function (field, newValue, oldValue, eOpts) {
                                    makeNameString(newValue)
                                }
                            }
                            }
                            , {
                                xtype: 'textfield'
                                , itemId: 'firstName'
                                , name: 'firstName'
                                , labelWidth: 40
                                , fieldLabel: 'Имя'
                                , width: '33%'
                                , margin: '10 10 10 0'
                                , maskRe: /[А-яЁё]/
                                , allowBlank :false
                                , listeners: {
                                    change: function (field, newValue, oldValue, eOpts) {
                                        makeNameString(newValue)
                                    }
                                }
                            }
                            , {
                                xtype: 'textfield'
                                , itemId: 'secondName'
                                , name: 'secondName'
                                , labelWidth: 60
                                , fieldLabel: 'Отчество'
                                , width: '33%'
                                , margin: '10 10 10 0'
                                , maskRe: /[А-яЁё]/
                                , listeners: {
                                    change: function (field, newValue, oldValue, eOpts) {
                                        makeNameString(newValue)
                                    }
                                }
                            }
                        ]
                    }
                    ,{
                        xtype: 'fieldset'
                        , border: 0
                        , width: '100%'
                        , layout: 'hbox'
                        , items: [
                            {
                                xtype: 'datefield'
                                , itemId: 'birthDay'
                                , name: 'birthDay'
                                , width: '40%'
                                , fieldLabel: 'Дата рождения'
                                , labelWidth: 100
                                , allowBlank: false
                                , margin: '10 20 10 10'
                            }
                        ]
                    }
                    ,{
                        xtype: 'fieldset'
                        , border: 0
                        , layout: 'hbox'
                        , width:'100%'
                        , items: [
                            {
                                xtype:'textfield'
                                ,itemId:'pasportId'
                                ,name:'pasportId'
                                ,hidden:true
                            }
                            ,{
                                xtype: 'textfield'
                                , itemId: 'seriesDoc'
                                , name: 'seriesDoc'
                                , minLength: 4
                                , maxLength: 4
                                , maskRe: /[0-9]/
                                , allowBlank: false
                                , labelWidth: 150
                                , fieldLabel: 'Паспорт &nbsp &nbsp серия'
                                , margin: '10 20 10 10'
                                , listeners: {
                                    change: function (field, newValue, oldValue, eOpts) {
                                        checkInputFieldInt(newValue)
                                    }
                                }
                            }
                            , {
                                xtype: 'textfield'
                                , itemId: 'numDoc'
                                , name: 'numDoc'
                                , minLength: 6
                                , maxLength: 6
                                , maskRe: /[0-9]/
                                , allowBlank: false
                                , labelWidth: 30
                                , fieldLabel: '№'
                                , margin: '10 0 10 10'
                                , listeners: {
                                    change: function (field, newValue, oldValue, eOpts) {
                                        checkInputFieldInt(newValue)
                                    }
                                }
                            }
                        ]
                    }
                    ,{
                        xtype: 'fieldset'
                        , border: 0
                        , margin: '20 0 30 0'
                        , width: '100%'
                        , layout: {
                            type: 'vbox',
                            align: 'center'
                        }
                        , items: [
                            {
                                xtype: 'fieldset'
                                , border: 0
                                , layout:'hbox'
                                , items: [
                                {
                                    xtype: 'button'
                                    ,text : 'Сохранить'
                                    , margin: '0 20 0 0'
                                    ,handler:function (btn) {
                                        var form = Ext.getCmp('Man.changeManForm');
                                        if(form.isValid()) {
                                            var values = form.getValues();
                                            values.manId = manId;
                                            Ext.Ajax.request({
                                                url: '<spring:url value="/page/man/create"/>'
                                                , params:values
                                                , method: 'POST'
                                                , success: function (resp, opts) {
                                                    Man.searchWindow(mask);
                                                    Man.loadStore();
                                                    windowChange.close();
                                                }
                                                , failure: function (resp, opts) {
                                                    alert("Ошибка")
                                                }
                                            });
                                        }
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,text : 'Отменить'
                                    ,handler:function (btn) {
                                        windowChange.close();
                                        mask.hide();
                                    }
                                }
                            ]
                            }
                        ]
                    }
                ]
                }
            ]
        });

    };

</script>

