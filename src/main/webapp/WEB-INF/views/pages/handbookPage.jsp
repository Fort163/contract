<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">

    Ext.namespace( 'HandbookForm');

    Ext.define('Handbook.Model', {
        extend: 'Ext.data.Model'
        ,fields: [
            {name: 'name',type:'string'}
            ,{name:'coefficient',type:'float'}
            ,{name: 'id',type:'int'}
        ]
    });

    HandbookForm.panel  = function (listUrl,createUrl,archiveUrl) {
        return Ext.create('Ext.panel.Panel', {
            renderTo: 'contractMainDiv'
            , layout: 'fit'
            , title: "Справочники"
            , border: 0
            , items:[
                {
                    xtype: 'fieldset'
                    ,border: 0
                    ,layout: {
                        type: 'vbox',
                        align: 'stretch'
                    }
                    ,items: [
                        {
                            xtype:'form'
                            ,id:'HandbookMainForm'
                            ,border:0
                            ,layout:'vbox'
                            ,items:[
                                {
                                    xtype:'textfield'
                                    ,itemId:'id'
                                    ,name:'id'
                                    ,hidden:true
                                }
                                ,{
                                    xtype:'textfield'
                                    ,allowBlank:false
                                    ,itemId:'name'
                                    ,name:'name'
                                    ,labelWidth:150
                                    ,fieldLabel:'Наименование'
                                    ,margin:'20 0 20 10'
                                    ,listeners: {
                                        change:function( field, newValue, oldValue, eOpts ){
                                            field.up('form').getComponent('id').setValue(null);
                                        }
                                    }
                                }
                                ,{
                                    xtype:'numberfield'
                                    ,itemId:'coefficient'
                                    ,name:'coefficient'
                                    ,minValue:0
                                    ,hideTrigger:true
                                    ,allowBlank:false
                                    ,decimalSeparator:'.'
                                    ,labelWidth:150
                                    ,fieldLabel:'Коэффициент'
                                    ,margin:'0 0 30 10'
                                }
                            ]
                            ,fbar:[
                                {
                                    xtype: 'button'
                                    ,text : 'Сохранить'
                                    ,handler:function (btn) {
                                        var form = Ext.getCmp('HandbookMainForm');
                                        if(form.isValid()) {
                                            Ext.Ajax.request({
                                                url: createUrl
                                                , params:form.getValues()
                                                , method: 'POST'
                                                , success: function (resp, opts) {
                                                    if(resp.responseText==='true') {
                                                        var store = Ext.StoreManager.lookup('Store.handbook.grid');
                                                        store.load();
                                                    }
                                                    else{
                                                        alert('Запись существует')
                                                    }
                                                }
                                                , failure: function (resp, opts) {
                                                    alert("Ошибка")
                                                }
                                            });
                                        }
                                    }
                                }
                            ]
                        }
                        ,{
                            xtype: 'grid'
                            ,id: 'Handbook.grid'
                            ,store: {
                                autoLoad: true
                                ,storeId:'Store.handbook.grid'
                                ,model:'Handbook.Model'
                                ,proxy:{
                                    type:'ajax'
                                    ,url:listUrl
                                    ,reader:{
                                        type:'json'
                                        ,root:'result'
                                    }
                                }
                            }
                            ,columns:{
                                items:[
                                    {
                                        text:'Наименование'
                                        ,dataIndex:'name'
                                        ,flex:3
                                    }
                                    ,{
                                        text:'Коэффициент'
                                        ,dataIndex:'coefficient'
                                        ,flex:1
                                    }
                                ]
                            }
                            ,tbar:[
                                {
                                    xtype: 'button'
                                    ,text : 'Удалить'
                                    ,handler:function (btn) {
                                        var selection = HandbookForm.getSelection();
                                        if(selection){
                                            Ext.Ajax.request({
                                                url: archiveUrl
                                                , params: {
                                                    id:selection.data.id
                                                }
                                                , method: 'GET'
                                                , success: function (resp, opts) {
                                                    var store = Ext.StoreManager.lookup('Store.handbook.grid');
                                                    store.load();
                                                }
                                                , failure: function (resp, opts) {
                                                    alert("Ошибка")
                                                }
                                            });
                                        }
                                    }
                                }
                            ]
                            ,listeners:{
                                itemclick:function( grid, record, item, index, e, eOpts ){
                                    var form = Ext.getCmp('HandbookMainForm');
                                    form.getForm().setValues(record.data);
                                }
                            }
                        }
                    ]
                }
            ]
        });
    };

    HandbookForm.getSelection = function () {
        var grid =Ext.getCmp('Handbook.grid');
        return grid.getSelectionModel().getSelection()[0];
    };

    Ext.onReady(function () {
        var listUrl;
        var createUrl;
        var archiveUrl;
        switch (${token}){
            case 1 :
                listUrl = '<spring:url value="/page/handbook/getPropertyTypeList"/>';
                createUrl = '<spring:url value="/page/handbook/createPropertyType"/>';
                archiveUrl =  '<spring:url value="/page/handbook/archivePropertyType"/>';
            break;
            case 2 :
                listUrl =  '<spring:url value="/page/handbook/getCoefficientConstructionList"/>';
                createUrl = '<spring:url value="/page/handbook/createCoefficientConstruction"/>';
                archiveUrl =  '<spring:url value="/page/handbook/archiveCoefficientConstruction"/>';
            break;
            case 3 :
                listUrl = '<spring:url value="/page/handbook/getCoefficientAreaList"/>';
                createUrl = '<spring:url value="/page/handbook/createCoefficientArea"/>';
                archiveUrl =  '<spring:url value="/page/handbook/archiveCoefficientArea"/>';
            break;
        }
        var panel = HandbookForm.panel(listUrl,createUrl,archiveUrl);
        panel.show();
        var oldResize = window.onresize;
        window.onresize = function () {
            if (oldResize) oldResize();
            panel.doLayout();
        };
    });

</script>
