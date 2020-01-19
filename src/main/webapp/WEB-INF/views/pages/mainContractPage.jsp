<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">

    Ext.namespace( 'MainContract');

    Ext.define('Standard.Model', {
        extend: 'Ext.data.Model'
        ,fields: [
            {name: 'val',  type: 'string'}
            ,{name: 'key',   type: 'int'}
        ]
    });

    Ext.define('MainContract.gridModel', {
        extend:'Ext.data.Model',
        fields:[
            {name:'id',type:'int'}
            ,{name:'fullName',type:'string'}
            ,{name:'number',type:'int'}
            ,{name:'dateConclusion',type:'string'}
            ,{name:'price',type:'float'}
            ,{name:'fromToDate',type:'string'}
        ]
    });

    MainContract.form  = function () {
        return Ext.create('Ext.panel.Panel', {
            renderTo: 'contractMainDiv'
            , id : 'ContractMainPanel'
            , layout: 'fit'
            , title: "Договоры"
            , border: 0
            , items:[
                    {
                        xtype: 'fieldset'
                        , border: 0
                        ,layout: {
                            type: 'vbox',
                            align: 'stretch'
                        }
                    , items: [
                        {
                            xtype: 'grid'
                            ,itemId: 'searchGrid'
                            ,store: {
                                autoLoad: true
                                ,storeId:'Store.contract.grid'
                                ,model:'MainContract.gridModel'
                                ,proxy:{
                                    type:'ajax'
                                    ,url:'<spring:url value="/page/contract/getList"/>'
                                    ,reader:{
                                        type:'json'
                                        ,root:'result'
                                    }
                                }
                            }
                            ,columns:{
                                items:[
                                    {
                                        text:'Номер'
                                        ,dataIndex:'number'
                                        ,flex:1
                                    }
                                    ,{
                                        text:'Дата заключения'
                                        ,dataIndex:'dateConclusion'
                                        ,flex:1
                                    }
                                    ,{
                                        text:'Страхователь'
                                        ,dataIndex:'fullName'
                                        ,flex:1
                                    }
                                    ,{
                                        text:'Премия'
                                        ,dataIndex:'price'
                                        ,flex:1
                                    }
                                    ,{
                                        text:'Срок действия'
                                        ,dataIndex:'fromToDate'
                                        ,flex:1
                                    }
                                ]
                            }
                            ,listeners:{
                                itemdblclick:function( grid, record, item, index, e, eOpts ){
                                    alert('a');
                                   /* Ext.Ajax.request({
                                        url: 'spring:url value="/page/agreementOrg/openSupplier"/>'
                                        , params: {
                                            id:record.data.id
                                        }
                                        , method: 'POST'
                                        , success: function (resp, opt) {

                                        }
                                        , failure: function (resp, opt) {
                                            alert('b');
                                        }
                                    });*/
                                }
                            }
                        }
                    ]
                }
            ]
        });
    };

    Ext.onReady(function () {
        var panel = MainContract.form();
        panel.show();
        var oldResize = window.onresize;
        window.onresize = function () {
            if (oldResize) oldResize();
            panel.doLayout();
        };
    });

</script>