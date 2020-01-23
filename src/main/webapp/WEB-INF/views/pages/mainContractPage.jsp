<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">

    Ext.namespace( 'MainContract');

    Ext.define('MainContract.gridModel', {
        extend:'Ext.data.Model',
        fields:[
            {name:'id',type:'int'}
            ,{name:'fullName',type:'string'}
            ,{name:'num',type:'int'}
            ,{name:'dateConclusion',type:'string'}
            ,{name:'insurancePremium',type:'float'}
            ,{name:'fromToDate',type:'string'}
        ]
    });

    MainContract.panel  = function () {
        return Ext.create('Ext.panel.Panel', {
            renderTo: 'contractMainDiv'
            , id : 'ContractMainPanel'
            , layout: 'fit'
            , title: "Договоры"
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
                            xtype: 'grid'
                            ,itemId: 'searchGrid'
                            ,store: {
                                autoLoad: true
                                ,storeId:'Store.contract.grid'
                                ,model:'MainContract.gridModel'
                                ,proxy:{
                                    type:'ajax'
                                    ,url:'<spring:url value="/page/contract/getContractList"/>'
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
                                        ,dataIndex:'num'
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
                                        ,dataIndex:'insurancePremium'
                                        ,flex:1
                                    }
                                    ,{
                                        text:'Срок действия'
                                        ,dataIndex:'fromToDate'
                                        ,flex:1
                                    }
                                ]
                            }
                            ,tbar:[
                                {
                                    xtype: 'button'
                                    ,text : 'Создать договор'
                                    ,handler:function (btn) {
                                        document.location.href = '<spring:url value="/createContractPage"/>'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,text : 'Открыть договор'
                                    ,handler:function (btn) {
                                        var grid = btn.up('grid');
                                        var selection = grid.getSelectionModel().getSelection()[0];
                                        document.location.href = '<spring:url value="/createContractPage"/>'+'?contractId='+selection.data.id
                                    }
                                }
                            ]
                            ,listeners:{
                                itemdblclick:function( grid, record, item, index, e, eOpts ){
                                    document.location.href = '<spring:url value="/createContractPage"/>'+'?contractId='+record.data.id
                                }
                            }
                        }
                    ]
                }
            ]
        });
    };

    Ext.onReady(function () {
        var panel = MainContract.panel();
        panel.show();
        var oldResize = window.onresize;
        window.onresize = function () {
            if (oldResize) oldResize();
            panel.doLayout();
        };
    });

</script>