/**
 * common extjs overrides
 */
Ext.apply(Ext,{
    BLANK_IMAGE_URL:"../../img/ext42/s.gif"
});
Ext.apply(Ext.Ajax,{
    timeout:6*60*1000
});
Ext.apply(Ext.data.Connection,{
    timeout:6*60*1000
});
Ext.apply(Ext.Loader,{
    disableCaching:true
});
Ext.apply(Ext.form.Basic,{
    timeout:6*60
});
var piP=/^\d{6}$/;
Ext.apply(Ext.form.field.VTypes, {
    //  vtype validation function
    postindex:function(val, field) {
        return piP.test(val);
    },
    postindexText:'Неверный формат индекса. Пример: 443000',
    postindexMask:/\d/i
});
Ext.apply(Ext.form.field.VTypes, {
    //  vtype validation function
    numeric:function(val, field) {
        return /^\d$/.test(val);
    },
    numericText:'Неверный формат числа. Введите целое число',
    numericMask:/\d/i
});
Ext.apply(Ext.form.field.VTypes, {
    //  vtype validation function
    numerics:function(val, field) {
        return /^\d*$/.test(val);
    },
    numericsText:'Неверный формат числа. Введите целое число',
    numericsMask:/\d/i
});
 Ext.apply(Ext.form.field.VTypes, {
    //  vtype validation function
    currency:function(val, field) {
        return /[0-9]\d{0,2}(\,\d{1,2})?%?$/.test(val);
    },
    currencyText:'Неверный формат числа. Число должно иметь вид 253,96',
    currencyMask:/[\d\,]/i
});
Ext.define('Ext.ux.ItemSelectorBugFix', {
    override: 'Ext.ux.ItemSelector'
    ,onBindStore: function(store, initial) {
        var me = this;

        if (me.fromField) {
            me.fromField.store.removeAll()
            me.toField.store.removeAll();

            // Add everything to the from field as soon as the Store is loaded
            if (store.getCount()) {
                me.populateFromStore(store);
            } else {
            // On dynamic store load this part throw double store population and as result - exception on binded view
            //   me.store.on('load', me.populateFromStore, store);
            }
        }
    }
});