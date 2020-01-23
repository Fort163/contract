<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="manPage.jsp"%>

<script type="text/javascript">

    Ext.namespace( 'CreateContract');

    Ext.define('Handbook.Model', {
        extend: 'Ext.data.Model'
        ,fields: [
            {name: 'name',  type: 'string'}
            ,{name: 'id',   type: 'int'}
        ]
    });

    CreateContract.clearCalculation = function(){
        var calculationResult = Ext.getCmp('CalculationForm').getComponent('calculationResult');
        var calculationDate = calculationResult.getComponent('calculationDate');
        var insurancePremium = calculationResult.getComponent('insurancePremium');
        calculationDate.reset();
        insurancePremium.reset();
    };

    CreateContract.panel = function(contractId){
        var panel= Ext.create('Ext.form.Panel', {
            renderTo: 'contractMainDiv'
            , id : 'ContractCreateForm'
            , layout: 'fit'
            , title: "Оформление договора"
            , border: 0
            , items:[
                , {
                    xtype: 'fieldset'
                    , border: 0
                    , layout: 'vbox'
                    , items: [
                        {
                            xtype: 'fieldset'
                            , border: 1
                            , layout: 'vbox'
                            , width:'100%'
                            , title: 'Расчет'
                            , margin: '20 20 40 20'
                            , items: [
                                {
                                    xtype: 'form'
                                    , id: 'CalculationForm'
                                    , border: 0
                                    , width: '100%'
                                    , layout: 'vbox'
                                    , items: [
                                        {
                                            xtype: 'form'
                                            , itemId: 'calculation'
                                            , border: 0
                                            , width: '100%'
                                            , layout: 'vbox'
                                            , items: [
                                                {
                                                    xtype: 'fieldset'
                                                    , border: 0
                                                    , width: '100%'
                                                    , layout: 'hbox'
                                                    , items: [
                                                        {
                                                            xtype:'textfield'
                                                            ,itemId:'contractId'
                                                            ,name:'contractId'
                                                            ,hidden:true
                                                        }
                                                        ,{
                                                            xtype: 'numberfield'
                                                            , itemId: 'insuranceAmount'
                                                            , name: 'insuranceAmount'
                                                            , minValue: 0
                                                            , hideTrigger: true
                                                            , allowDecimals: false
                                                            , allowBlank: false
                                                            , labelWidth: 150
                                                            , fieldLabel: 'Страховая сумма'
                                                            , margin: '10 20 10 10'
                                                            , listeners: {
                                                                change: function (field, newValue, oldValue, eOpts) {
                                                                    CreateContract.clearCalculation()
                                                                }
                                                            }
                                                        }
                                                        , {
                                                            xtype: 'datefield'
                                                            , itemId: 'periodFrom'
                                                            , name: 'periodFrom'
                                                            , fieldLabel: 'Срок действия с'
                                                            , minValue: new Date()
                                                            , labelWidth: 150
                                                            , allowBlank: false
                                                            , listeners: {
                                                                change: function (field, newValue, oldValue, eOpts) {
                                                                    CreateContract.clearCalculation()
                                                                    var periodTo = field.up().getComponent('periodTo');
                                                                    var dateMax = new Date((newValue.getFullYear() + 1), newValue.getMonth(), newValue.getDate());
                                                                    var dateMin = new Date(newValue.getFullYear(), newValue.getMonth(), (newValue.getDate() + 1));
                                                                    periodTo.setMinValue(dateMin);
                                                                    periodTo.setMaxValue(dateMax);
                                                                }
                                                            }
                                                            , margin: '10 20 10 0'
                                                        }
                                                        , {
                                                        xtype: 'datefield'
                                                        , itemId: 'periodTo'
                                                        , name: 'periodTo'
                                                        , fieldLabel: 'по'
                                                        , labelWidth: 30
                                                        , allowBlank: false
                                                        , margin: '10 20 10 0'
                                                        , listeners: {
                                                            change: function (field, newValue, oldValue, eOpts) {
                                                                CreateContract.clearCalculation()
                                                            }
                                                        }
                                                    }
                                                    ]
                                                }
                                                , {
                                                    xtype: 'combo'
                                                    , width: '30%'
                                                    , margin: '10 0 10 20'
                                                    , itemId: 'propertyType'
                                                    , name: 'propertyType'
                                                    , editable: false
                                                    , valueField: 'id'
                                                    , displayField: 'name'
                                                    , fieldLabel: 'Тип недвижимости'
                                                    , labelWidth: 150
                                                    , allowBlank: false
                                                    , store: {
                                                        autoLoad: true
                                                        , storeId: 'Store.propertyType'
                                                        , model: 'Handbook.Model'
                                                        , proxy: {
                                                            type: 'ajax'
                                                            , url: '<spring:url value="/page/handbook/getPropertyTypeList"/>'
                                                            , reader: {
                                                                type: 'json'
                                                                , root: 'result'
                                                            }
                                                        }
                                                    }
                                                    , listeners: {
                                                        change: function (field, newValue, oldValue, eOpts) {
                                                            CreateContract.clearCalculation()
                                                        }
                                                    }
                                                }
                                                , {
                                                    xtype: 'combo'
                                                    , margin: '10 0 10 20'
                                                    , itemId: 'coefficientArea'
                                                    , name: 'coefficientArea'
                                                    , editable: false
                                                    , valueField: 'id'
                                                    , displayField: 'name'
                                                    , fieldLabel: 'Площадь, кв.м.'
                                                    , labelWidth: 150
                                                    , width: '30%'
                                                    , allowBlank: false
                                                    , store: {
                                                        autoLoad: true
                                                        , storeId: 'Store.coefficientArea'
                                                        , model: 'Handbook.Model'
                                                        , proxy: {
                                                            type: 'ajax'
                                                            , url: '<spring:url value="/page/handbook/getCoefficientAreaList"/>'
                                                            , reader: {
                                                                type: 'json'
                                                                , root: 'result'
                                                            }
                                                        }
                                                    }
                                                    , listeners: {
                                                        change: function (field, newValue, oldValue, eOpts) {
                                                            CreateContract.clearCalculation()
                                                        }
                                                    }
                                                }
                                                , {
                                                    xtype: 'combo'
                                                    , margin: '10 0 30 20'
                                                    , itemId: 'coefficientConstruction'
                                                    , name: 'coefficientConstruction'
                                                    , editable: false
                                                    , valueField: 'id'
                                                    , displayField: 'name'
                                                    , fieldLabel: 'Год постройки'
                                                    , labelWidth: 150
                                                    , width: '30%'
                                                    , allowBlank: false
                                                    , store: {
                                                        autoLoad: true
                                                        , storeId: 'Store.сoefficientConstruction'
                                                        , model: 'Handbook.Model'
                                                        , proxy: {
                                                            type: 'ajax'
                                                            ,
                                                            url: '<spring:url value="/page/handbook/getCoefficientConstructionList"/>'
                                                            ,
                                                            reader: {
                                                                type: 'json'
                                                                , root: 'result'
                                                            }
                                                        }
                                                    }
                                                    , listeners: {
                                                        change: function (field, newValue, oldValue, eOpts) {
                                                            CreateContract.clearCalculation()
                                                        }
                                                    }
                                                }
                                                , {
                                                    xtype: 'fieldset'
                                                    , width: '100%'
                                                    , border: 0
                                                    , layout: {
                                                        type: 'vbox',
                                                        align: 'center'
                                                    }
                                                    , items: [
                                                        {
                                                            xtype: 'button'
                                                            , text: 'Расчитать'
                                                            , margin: '10 0 0 48%'
                                                            //,cls:'btncls'
                                                            , handler: function (btn) {
                                                                var form = btn.up('form');
                                                                if (form.isValid()) {
                                                                    var mask = new Ext.LoadMask(form, {msg: "Расчет..."});
                                                                    mask.show();
                                                                    Ext.Ajax.request({
                                                                        url: '<spring:url value="/page/contract/calculation"/>'
                                                                        , params: form.getValues()
                                                                        , method: 'POST'
                                                                        , success: function (resp, opts) {
                                                                            var result = Ext.decode(resp.responseText);
                                                                            var form = Ext.getCmp('CalculationForm');
                                                                            form.getForm().setValues(result)
                                                                            mask.hide();
                                                                        }
                                                                        , failure: function (resp, opts) {

                                                                        }
                                                                    });
                                                                }
                                                            }
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                        , {
                                            xtype: 'fieldset'
                                            , itemId:'calculationResult'
                                            , width: '100%'
                                            , border: 0
                                            , layout: 'hbox'
                                            , items: [
                                                {
                                                    xtype: 'datefield'
                                                    , itemId: 'calculationDate'
                                                    , name: 'calculationDate'
                                                    , fieldLabel: 'Дата расчета'
                                                    , labelWidth: 130
                                                    , allowBlank: false
                                                    , readOnly: true
                                                    , margin: '10 20 10 10'
                                                }
                                                , {
                                                    xtype: 'numberfield'
                                                    , itemId: 'insurancePremium'
                                                    , name: 'insurancePremium'
                                                    , minValue: 0
                                                    , hideTrigger: true
                                                    , readOnly: true
                                                    , allowBlank: false
                                                    , decimalSeparator:'.'
                                                    , labelWidth: 150
                                                    , fieldLabel: 'Страховая премия'
                                                    , margin: '10 20 10 250'
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                        ,{
                            xtype: 'fieldset'
                            , border: 0
                            , layout: 'vbox'
                            , width:'100%'
                            , margin: '20 20 20 20'
                            , items: [
                                {
                                    xtype: 'form'
                                    , id:'ContractForm'
                                    , border: 0
                                    , width: '100%'
                                    , layout: 'vbox'
                                    , items: [
                                        {
                                            xtype: 'fieldset'
                                            , border: 0
                                            , layout: 'hbox'
                                            , items: [
                                                {
                                                    xtype: 'textfield'
                                                    , itemId: 'num'
                                                    , name: 'num'
                                                    , minLength: 6
                                                    , maxLength: 6
                                                    , maskRe: /[0-9]/
                                                    , allowBlank: false
                                                    , labelWidth: 150
                                                    , fieldLabel: '№ Договора'
                                                    , margin: '10 50 10 0'
                                                    , listeners: {
                                                        change: function (field, newValue, oldValue, eOpts) {
                                                            checkInputFieldInt(newValue)
                                                        }
                                                    }
                                                }
                                                ,{
                                                    xtype: 'datefield'
                                                    , itemId: 'conclusionDate'
                                                    , name: 'conclusionDate'
                                                    , fieldLabel: 'Дата заключения'
                                                    , labelWidth: 150
                                                    , allowBlank: false
                                                    , readOnly: true
                                                    , value: new Date()
                                                    , margin: '10 20 10 0'
                                                }
                                            ]
                                        }
                                        ,{
                                            xtype: 'fieldset'
                                            , title:'<h2>Страхователь</h2>'
                                            , border: 0
                                            , layout: 'hbox'
                                            , width:'100%'
                                            , items: [
                                                {
                                                    xtype:'textfield'
                                                    ,itemId:'manId'
                                                    ,name:'manId'
                                                    ,hidden:true
                                                }
                                                ,{
                                                    xtype: 'button'
                                                    , text: 'Выбрать'
                                                    , margin: '10 20 0 0'
                                                    , handler: function (btn) {
                                                        var form = Ext.getCmp('ContractCreateForm');
                                                        var mask = new Ext.LoadMask(form, {msg: "Поиск человека..."});
                                                        mask.show();
                                                        Man.searchWindow(mask);
                                                    }
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    , itemId: 'fullName'
                                                    , name: 'fullName'
                                                    , allowBlank: false
                                                    , readOnly: true
                                                    , labelWidth: 50
                                                    , width:'60%'
                                                    , fieldLabel: 'ФИО'
                                                    , margin: '10 20 10 0'
                                                    , listeners: {
                                                        change: function (field, newValue, oldValue, eOpts) {
                                                            var btn = Ext.getCmp('Button.change');
                                                            btn.setDisabled(false);
                                                        }
                                                    }
                                                }
                                                ,{
                                                    xtype: 'button'
                                                    ,id:'Button.change'
                                                    , text: 'Изменить'
                                                    , disabled:true
                                                    , margin: '10 20 0 0'
                                                    , handler: function (btn) {
                                                        var form = Ext.getCmp('ContractCreateForm');
                                                        var mask = new Ext.LoadMask(form, {msg: "Клиент..."});
                                                        mask.show();
                                                        var manId = btn.up('fieldset').getComponent('manId');
                                                        Man.changeWindow(mask,manId.getValue());
                                                    }
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
                                                    xtype: 'datefield'
                                                    , itemId: 'birthDay'
                                                    , name: 'birthDay'
                                                    , fieldLabel: 'Дата рождения'
                                                    , labelWidth: 150
                                                    , allowBlank: false
                                                    , readOnly:true
                                                    , margin: '10 20 10 0'
                                                }
                                                , {
                                                    xtype: 'textfield'
                                                    , itemId: 'seriesDoc'
                                                    , name: 'seriesDoc'
                                                    , minLength: 4
                                                    , maxLength: 4
                                                    , maskRe: /[0-9]/
                                                    , allowBlank: false
                                                    , readOnly:true
                                                    , labelWidth: 150
                                                    , fieldLabel: 'Паспорт &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp серия'
                                                    , margin: '10 20 10 0'
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
                                                    , readOnly:true
                                                    , labelWidth: 30
                                                    , fieldLabel: '№'
                                                    , margin: '10 0 10 0'
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
                                            , title:'<h2>Адрес недвижимости</h2>'
                                            , border: 0
                                            , layout: 'hbox'
                                            , width:'100%'
                                            , items: [
                                                {
                                                    xtype:'textfield'
                                                    ,itemId:'addressId'
                                                    ,name:'addressId'
                                                    ,hidden:true
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    , itemId: 'state'
                                                    , name: 'state'
                                                    , allowBlank: false
                                                    , labelWidth: 90
                                                    , fieldLabel: 'Государство'
                                                    , margin: '10 20 10 20'
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    , itemId: 'index'
                                                    , name: 'index'
                                                    , allowBlank: false
                                                    , maskRe: /[0-9]/
                                                    , labelWidth: 70
                                                    , fieldLabel: 'Индекс'
                                                    , margin: '10 20 10 0'
                                                    , listeners: {
                                                        change: function (field, newValue, oldValue, eOpts) {
                                                            checkInputFieldInt(newValue)
                                                        }
                                                    }
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    , itemId: 'region'
                                                    , name: 'region'
                                                    , allowBlank: false
                                                    , labelWidth: 70
                                                    , fieldLabel: 'Область'
                                                    , margin: '10 20 10 0'
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    , itemId: 'district'
                                                    , name: 'district'
                                                    , allowBlank: false
                                                    , labelWidth: 70
                                                    , fieldLabel: 'Район'
                                                    , margin: '10 20 10 0'
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
                                                    xtype: 'textfield'
                                                    , itemId: 'city'
                                                    , name: 'city'
                                                    , allowBlank: false
                                                    , labelWidth: 130
                                                    , fieldLabel: 'Населенный пункт'
                                                    , margin: '10 20 10 0'
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    , itemId: 'street'
                                                    , name: 'street'
                                                    , allowBlank: false
                                                    , labelWidth: 90
                                                    , fieldLabel: 'Улица'
                                                    , margin: '10 20 10 0'
                                                }
                                                , {
                                                    xtype: 'textfield'
                                                    , itemId: 'house'
                                                    , name: 'house'
                                                    , maskRe: /[0-9]/
                                                    , allowBlank: false
                                                    , labelWidth: 50
                                                    , width:130
                                                    , fieldLabel: 'Дом'
                                                    , margin: '10 20 10 0'
                                                    , listeners: {
                                                        change: function (field, newValue, oldValue, eOpts) {
                                                            checkInputFieldInt(newValue)
                                                        }
                                                    }
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    , itemId: 'housing'
                                                    , name: 'housing'
                                                    , labelWidth: 70
                                                    , width:150
                                                    , fieldLabel: 'Корпус'
                                                    , margin: '10 20 10 0'
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    , itemId: 'building'
                                                    , name: 'building'
                                                    , labelWidth: 70
                                                    , width:150
                                                    , fieldLabel: 'Строение'
                                                    , margin: '10 20 10 0'
                                                }
                                                , {
                                                    xtype: 'textfield'
                                                    , itemId: 'flat'
                                                    , name: 'flat'
                                                    , maskRe: /[0-9]/
                                                    , labelWidth: 70
                                                    , width:150
                                                    , fieldLabel: 'Квартира'
                                                    , margin: '10 20 10 0'
                                                    , listeners: {
                                                        change: function (field, newValue, oldValue, eOpts) {
                                                            checkInputFieldInt(newValue)
                                                        }
                                                    }
                                                }
                                            ]
                                        }
                                    ]
                                }
                                , {
                                    xtype: 'fieldset'
                                    , title: '<h2>Комментарий</h2>'
                                    , border: 0
                                    , layout: 'vbox'
                                    , width: '100%'
                                    , items: [
                                        {
                                            xtype: 'textareafield'
                                            ,grow: true
                                            ,name: 'comment'
                                            ,labelWidth: 90
                                            ,fieldLabel: 'Комментарий к договору(не печатается на полисе)'
                                            , width:'100%'
                                        }
                                    ]
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
                                                    ,text : 'Сохранить'
                                                    , margin: '0 20 0 0'
                                                    ,handler:function (btn) {
                                                        var form = Ext.getCmp('ContractCreateForm');
                                                        if(form.isValid()) {
                                                            var values = form.getValues();
                                                            var mask = new Ext.LoadMask(form, {msg: "Создание договора..."});
                                                            mask.show();
                                                            Ext.Ajax.request({
                                                                url: '<spring:url value="/page/contract/createContract"/>'
                                                                , params:values
                                                                , method: 'POST'
                                                                , success: function (resp, opts) {
                                                                    mask.hide();
                                                                    alert("Успешно сохранено")
                                                                }
                                                                , failure: function (resp, opts) {
                                                                    alert("Ошибка")
                                                                }
                                                            });
                                                        }
                                                        else {
                                                            alert('Заполните обязательные поля')
                                                        }
                                                    }
                                                }
                                                ,{
                                                    xtype: 'button'
                                                    ,text : 'К списку договоров'
                                                    , margin: '0 20 0 0'
                                                    ,handler:function (btn) {
                                                        document.location.href = '<spring:url value="/mainContractPage"/>'
                                                    }
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        });
        if(contractId){
            Ext.Ajax.request({
                url: '<spring:url value="/page/contract/openContract"/>'
                ,params:{
                    contractId:contractId
                }
                ,method:'GET'
                ,success: function(resp, opts) {
                    var result = Ext.decode(resp.responseText);
                    var form = Ext.getCmp('ContractCreateForm');
                    form.getForm().setValues(result);
                    return panel;
                }
                ,failure: function(resp, opts) {
                    alert('Ошибка')
                }
            });
        }
        else {
            return panel;
        }
    };

    Ext.onReady(function () {
        var panel = CreateContract.panel(${contractId});
        panel.show();
        var oldResize = window.onresize;
        window.onresize = function () {
            if (oldResize) oldResize();
            panel.doLayout();
        };
    });
</script>
