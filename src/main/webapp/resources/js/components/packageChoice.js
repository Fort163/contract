Ext.define('Register.package.date.Model', {
    extend:'Ext.data.Model',
    fields:[
        {name:'year',type:'int'}
        ,{name:'monthNumber',type:'int'}
        ,{name:'monthName',type:'string'}
    ]
});
function deepCloneStore (source) {
    var target = Ext.create ('Ext.data.Store', {
        model: source.model
    });
    Ext.each (source.getRange (), function (record) {
        var newRecordData = Ext.clone (record.copy().data);
        var model = new source.model (newRecordData, newRecordData.id);
        target.add (model);
    });
    return target;
}
Ext.define('Ext.ux.upload.PackageChoice', {
    extend: 'Ext.form.FieldSet',
    config :
        {
            textYear:null,
            loadUrl : ''
        },
    constructor : function(config) {
        this.initConfig(config);
        return this.callParent(arguments);
    },
    initComponent: function()
    {
       // this.textYear  = Ext.create(' Ext.form.field.Text', {  fieldLabel: 'Email Address'});
        Ext.apply(this, {
            renderTo: Ext.getBody()
                ,layout:'hbox'
                ,border:0
                , margin: '5 0 0 0'
                ,items :[
                {
                    xtype:'combobox'
                    ,width: 70
                    , emptyText: 'Год'
                    , itemId: 'year'
                    , name: 'year'
                    , displayField: 'year'
                    , valueField: 'year'
                    , margin: '5 15 0 10'
                    , value: ''
                    , lastQuery: ''
                    , store: {
                    autoLoad: true
                    , autoDestroy: true
                    , storeId: 'Search.Beneficiaries.Normative.Year'
                    , model: 'Register.package.date.Model'
                    ,proxy: {
                        type: 'ajax'
                        , url: this.loadUrl
                        , reader: {
                            type: 'json'
                            , root: 'results'
                        }
                    }
                    , listeners: {
                        load: function (store) {
                            store.clearFilter(true);
                            loadStore = deepCloneStore(store);
                            var years =[];
                            store.filter([
                                {
                                    filterFn: function (item) {
                                        if (!Ext.Array.contains(years, item.data.year)) {
                                            years.push(item.data.year);
                                            return true;
                                        }
                                        else {
                                            return false;
                                        }
                                    }
                                }
                            ])
                        }
                    }
                }
                 , listeners: {
                    change: function (combo, record, index) {
                        var store = Ext.data.StoreManager.lookup('Search.Beneficiaries.Normative.packageMonth');
                        loadStore.removeFilter();
                        loadStore.filter([
                            {
                                filterFn: function (item) {
                                    return record === item.data.year;
                                }
                            }
                        ]);
                        store.loadData(loadStore.data.items);
                        this.up().getComponent('month').enable(true);
                        this.up().getComponent('month').clearValue();
                    }
                 }
            }
           ,{
                xtype:'combobox'
                ,width:100
                ,itemId:'month'
                ,name:'month'
                ,queryMode: 'local'
                ,displayField:'monthName'
                ,valueField:'monthNumber'
                ,margin:'5 60 0 0'
                ,value:''
                ,emptyText: 'Месяц'
                ,autoLoad: false
                ,lastQuery: ''
                ,disabled:true
                ,store:{
                    autoDestroy: true
                    , storeId: 'Search.Beneficiaries.Normative.packageMonth'
                    , model: 'Register.package.date.Model'
                }
           }
                ,{
                    xtype: 'radiogroup',
                    vertical: true
                    ,margin:'5 0 0 0'
                    ,itemId:'service'
                    ,width:250
                    ,allowBlank:false
                    ,items:[
                        {boxLabel:'Все',name:'service',width:50,inputValue: 'all',checked:true}
                        ,{ boxLabel:'ЖКУ',name:'service',width:50,inputValue: 'hus'}
                        ,{ boxLabel:'Капремонт',name:'service',width:100,inputValue: 'repairs'}
                    ]
                    ,listeners:{
                        change: function( group, newValue, oldValue){
                            var serviceType;
                            if(newValue.service=== 'repairs')serviceType = 0;
                            else {
                                if(newValue.service==='hus')  serviceType =1;
                                else serviceType = null;
                            }
                            Ext.data.StoreManager.lookup('Search.Beneficiaries.Normative.Year').load({params:{serviceType:serviceType}});
                            this.up().getComponent('year').clearValue();
                            this.up().getComponent('month').disable(true);
                            this.up().getComponent('month').clearValue();
                        }
                    }
                }
           ]
        }
        );
        this.callParent(arguments);
    }

});

//Ext.reg('widget.itemselectorfield', Ext.ux.upload.PackageChoice);