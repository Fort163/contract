/**
 * Upload status bar.
 * 
 * @class Ext.ux.upload.StatusBar
 * @extends Ext.toolbar.Toolbar
 */
Ext.define('Ext.ux.upload.StatusBar', {
    extend : 'Ext.toolbar.Toolbar',

    config : {
        selectionMessageText : 'Выбрано {0} файл(ов), {1}',
        uploadMessageText : 'Прогресс загрузки {0}% ({1} of {2})',
        textComponentId : 'mu-status-text'
    },

    constructor : function(config) {
        this.initConfig(config);

        return this.callParent(arguments);
    },

    initComponent : function() {

        Ext.apply(this, {
            items : [
                {
                    xtype : 'tbtext',
                    id : this.textComponentId,
                    text : '&nbsp;'
                }
            ]
        });

        this.callParent(arguments);
    },

    setText : function(text) {
        this.getComponent(this.textComponentId).setText(text);
    },

    setSelectionMessage : function(fileCount, byteCount) {
        this.setText(Ext.String.format(this.selectionMessageText, fileCount, Ext.util.Format.fileSize(byteCount)));
    },

    setUploadMessage : function(progressPercent, uploadedFiles, totalFiles) {
        this.setText(Ext.String.format(this.uploadMessageText, progressPercent, uploadedFiles, totalFiles));
    }

});