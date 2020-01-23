Ext.namespace('Ext.ux');
Ext.namespace('Ext.ux.form');
Ext.namespace('Ext.ux.model');

//Ext.ux.Warn.js
/**
 * @class Ext.ux.Warn
 * Passive popup box (a toast) singleton
 * @singleton
 */
Ext.ux.Warn = function() {
    var msgCt,hideTask;

    function createBox(t, s){
        return ['<div class="msg">',
                '<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
                '<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><h3>', t, '</h3>', s, '</div></div></div>',
                '<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
                '</div>'].join('');
    }

    return {
        /**
               * @cfg {String} title warn's title
              */
        title:'Warn'
        /**
               * @cfg {String} message warn's message
              */
        ,message:''
        /**
               * @cfg {Mixed} el The context element
              */
        ,el:document.body
        /**
               * @cfg {int} delay how much time display a warn message
              */
        ,delay:10
        /**
               * @cfg {String} position (optional, defaults to "bl-bl") The position to align to.
              */
        ,alignTo:'bl-bl'
        /**
               * @cfg {Boolean} animate to use animation to show/hide a warn
              */
        ,animate:true
    /**
     * Shows popup
     * @member Ext.ux.Warn
     * @param {Object} config
     */
        ,msg:function(config){
            Ext.apply(this,config);
            if(!msgCt){
                msgCt = Ext.core.DomHelper.insertFirst(this.el, {id:'msg-div',style:'position:absolute;z-index:100000'}, true);
            }
            var m = Ext.core.DomHelper.append(msgCt, {html:createBox(this.title, this.message)}, true);
            msgCt.alignTo(this.el,this.alignTo);
            if(this.animate) m.slideIn('t').pause(this.delay).ghost('t', {remove:true});
            else {
                m.show();
                if (!hideTask) {
                    hideTask = new Ext.util.DelayedTask(m.hide, m);
                }
                hideTask.delay(this.delay*1000,function(){
                    m.hide();
                    m.remove();
                });
            }
        }
	}

}();
//not a singleton
Ext.ux.Warn2 = function(){
    var msgCt,hideTask;
    function createBox(t, s){
        return ['<div class="msg">',
                '<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
                '<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><h3>', t, '</h3>', s, '</div></div></div>',
                '<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
                '</div>'].join('');
    }
    return {
        /**
               * @cfg {String} title warn's title
              */
        title:'Warn'
        /**
               * @cfg {String} message warn's message
              */
        ,message:''
        /**
               * @cfg {Mixed} el The context element
              */
        ,el:document.body
        /**
               * @cfg {int} delay how much seconds display a warn message
              */
        ,delay:10
        /**
               * @cfg {String} position (optional, defaults to "bl-bl") The position to align to.
              */
        ,alignTo:'bl-bl'
        /**
        * @cfg {Boolean} animate to use animation to show/hide a warn
        */
        ,animate:true
        /**
         * @cfg {Boolean} animate to use animation to show/hide a warn
         */
        ,prevWindow:false
    /**
     * Shows popup
     * @member Ext.ux.Warn
     * @param {Object} config
     */
        ,msg:function(config){
            if(this.prevWindow) this.prevWindow.hide();
            var me = this;
            Ext.apply(this,config);
            if(!msgCt){
                msgCt = Ext.core.DomHelper.insertFirst(this.el, {id:'msg-div',style:'position:absolute;z-index:100000'}, true);
            }
            var m = Ext.core.DomHelper.append(msgCt, {html:createBox(this.title, this.message)}, true);
            this.prevWindow = m; 
            msgCt.alignTo(this.el,this.alignTo);
            if(this.animate) m.slideIn('t').pause(this.delay).ghost('t', {remove:true});
            else {
                m.show();
                if (!hideTask) {
                    hideTask = new Ext.util.DelayedTask(m.hide, m);
                }
                hideTask.delay(this.delay*1000,function(){
                    me.prevWindow = false; 
                    m.hide();
                    m.remove();
                });
            }
        }
	}
};

/**
 * Ext.ux.FileUploader
 *
 * @author  Ing. Jozef Sak?lo?
 * @version $Id: Ext.ux.all.js 11611 2012-04-11 13:36:16Z abolmasov $
 * @date    15. March 2008
 *
 * @license Ext.ux.FileUploader is licensed under the terms of
 * the Open Source LGPL 3.0 license.  Commercial use is permitted to the extent
 * that the code/component(s) do NOT become part of another Open Source or Commercially
 * licensed development library or toolkit without explicit permission.
 *
 * License details: http://www.gnu.org/licenses/lgpl.html
 */

/*global Ext */

/**
 * @class Ext.ux.FileUploader
 * @extends Ext.util.Observable
 * @constructor
 */
Ext.define('Ext.ux.FileUploader',{
    extend:'Ext.util.Observable'
    ,alias:'fileuploader'
    /**
     * @cfg {Object} baseParams baseParams are sent to server in each request.
     */
     ,baseParams:{cmd:'upload',dir:'.'}

    /**
     * @cfg {Boolean} concurrent true to start all requests upon upload start, false to start
     * the next request only if previous one has been completed (or failed). Applicable only if
     * singleUpload = false
     */
    ,concurrent:true

    /**
     * @cfg {Boolean} enableProgress true to enable querying server for progress information
     */
    ,enableProgress:true

    /**
     * @cfg {String} jsonErrorText Text to use for json error
     */
    ,jsonErrorText:'Cannot decode JSON object'

    /**
     * @cfg {Number} Maximum client file size in bytes
     */
    ,maxFileSize:524288

    /**
     * @cfg {String} progressIdName Name to give hidden field for upload progress identificator
     */
    ,progressIdName:'UPLOAD_IDENTIFIER'

    /**
     * @cfg {Number} progressInterval How often (in ms) is progress requested from server
     */
    ,progressInterval:2000

    /**
     * @cfg {String} progressUrl URL to request upload progress from
     */
    ,progressUrl:'progress.php'

    /**
     * @cfg {Object} progressMap Mapping of received progress fields to store progress fields
     */
    ,progressMap:{
         bytes_total:'bytesTotal'
        ,bytes_uploaded:'bytesUploaded'
        ,est_sec:'estSec'
        ,files_uploaded:'filesUploaded'
        ,speed_average:'speedAverage'
        ,speed_last:'speedLast'
        ,time_last:'timeLast'
        ,time_start:'timeStart'
    }
    /**
     * @cfg {Boolean} singleUpload true to upload files in one form, false to upload one by one
     */
    ,singleUpload:false
    /**
     * @cfg {String} unknownErrorText Text to use for unknow error
     */
    ,unknownErrorText:'Unknown error'
    /**
     * uploads in progress count
     * @private
     */
    ,upCount:0
    ,initComponent:function(){
        var me = this;
        me.addEvents()(me,[
        /**
		 * @event beforeallstart
		 * Fires before an upload (of all files) is started. Return false to cancel the event.
		 * @param {Ext.ux.FileUploader} this
		 */
		 'beforeallstart'
		/**
		 * @event allfinished
		 * Fires after upload (of all files) is finished
		 * @param {Ext.ux.FileUploader} this
		 */
		,'allfinished'
		/**
		 * @event beforefilestart
		 * Fires before the file upload is started. Return false to cancel the event.
		 * Fires only when singleUpload = false
		 * @param {Ext.ux.FileUploader} this
		 * @param {Ext.data.Record} record upload of which is being started
		 */
		,'beforefilestart'
		/**
		 * @event filefinished
		 * Fires when file finished uploading.
		 * Fires only when singleUpload = false
		 * @param {Ext.ux.FileUploader} this
		 * @param {Ext.data.Record} record upload of which has finished
		 */
		,'filefinished'
		/**
		 * @event progress
		 * Fires when progress has been updated
		 * @param {Ext.ux.FileUploader} this
		 * @param {Object} data Progress data object
		 * @param {Ext.data.Record} record Only if singleUpload = false
		 */
		,'progress']);
    }
    /**
     * creates form to use for upload.
     * @private
     * @return {Ext.Element} form
     */
    ,createForm:function(record) {
        var progressId = parseInt(Math.random() * 1e10, 10);
        var form = Ext.getBody().createChild({
             tag:'form'
            ,action:this.url
            ,method:'post'
            ,cls:'x-hidden'
            ,id:Ext.id()
            ,cn:[{
                 tag:'input'
                ,type:'hidden'
                ,name:'APC_UPLOAD_PROGRESS'
                ,value:progressId
            },{
                 tag:'input'
                ,type:'hidden'
                ,name:this.progressIdName
                ,value:progressId
            },{
                 tag:'input'
                 ,type:'hidden'
                 ,name:'ind'
                 ,value:this.store.indexOf(record)
            },{
                 tag:'input'
                ,type:'hidden'
                ,name:'MAX_FILE_SIZE'
                ,value:this.maxFileSize
            }]
        });
        if(record) {
            record.set('form', form);
            record.set('progressId', progressId);
        }
        else {
            this.progressId = progressId;
        }
        return form;

    }
    ,deleteForm:function(form, record) {
        form.remove();
        if(record) {
            record.set('form', null);
        }
    }
    /**
     * Fires event(s) on upload finish/error
     * @private
     */
    ,fireFinishEvents:function(options) {
        if(true !== this.eventsSuspended && !this.singleUpload) {
            this.fireEvent('filefinished', this, options && options.record);
        }
        if(true !== this.eventsSuspended && 0 === this.upCount) {
            this.stopProgress();
            this.fireEvent('allfinished', this);
        }
    }
    /**
     * Geg the iframe identified by record
     * @private
     * @param {Ext.data.Record} record
     * @return {Ext.Element} iframe or null if not found
     */
    ,getIframe:function(record) {
        var iframe = null;
        var form = record.get('form');
        if(form && form.dom && form.dom.target) {
            iframe = Ext.get(form.dom.target);
        }
        return iframe;
    }
    /**
     * returns options for Ajax upload request
     * @private
     * @param {Ext.data.Record} record
     * @param {Object} params params to add
     */
    ,getOptions:function(record, params) {
        var o = {
             url:this.url
            ,method:'post'
            ,isUpload:true
            ,scope:this
            ,callback:this.uploadCallback
            ,record:record
            ,params:this.getParams(record, params)
        };
        return o;
    }
    /**
     * get params to use for request
     * @private
     * @return {Object} params
     */
    ,getParams:function(record, params) {
        var p = {path:this.path};
        Ext.apply(p, this.baseParams || {}, params || {});
        return p;
    }
    /**
     * processes success response
     * @private
     * @param {Object} options options the request was called with
     * @param {Object} response request response object
     * @param {Object} o decoded response.responseText
     */
    ,processSuccess:function(options, response, o) {
        var record = false;

        // all files uploadded ok
        if(this.singleUpload) {
            this.store.each(function(r) {
                r.set('state', 'done');
                r.set('error', '');
                r.commit();
            });
        }
        else {
            record = options.record;
            record.set('state', 'done');
            record.set('error', '');
            record.commit();
        }
        this.deleteForm(options.form, record);
    }
	/**
	 * processes failure response
	 * @private
	 * @param {Object} options options the request was called with
	 * @param {Object} response request response object
	 * @param {String/Object} error Error text or JSON decoded object. Optional.
	 */
	,processFailure:function(options, response, error) {
		var record = options.record;
		var records;

		// singleUpload - all files uploaded in one form
		if(this.singleUpload) {
			// some files may have been successful
			records = this.store.queryBy(function(r){
				var state = r.get('state');
				return 'done' !== state && 'uploading' !== state;
			});
			records.each(function(record) {
				var e = error.errors ? error.errors[record.id] : this.unknownErrorText;
				if(e) {
					record.set('state', 'failed');
					record.set('error', e);
					Ext.getBody().appendChild(record.get('input'));
				}
				else {
					record.set('state', 'done');
					record.set('error', '');
				}
				record.commit();
			}, this);

			this.deleteForm(options.form);
		}
		// multipleUpload - each file uploaded in it's own form
		else {
			if(error && 'object' === Ext.type(error)) {
				record.set('error', error.errors && error.errors[record.id] ? error.errors[record.id] : this.unknownErrorText);
			}
			else if(error) {
				record.set('error', error);
			}
			else if(response && response.responseText) {
				record.set('error', response.responseText);
			}
			else {
				record.set('error', this.unknownErrorText);
			}
			record.set('state', 'failed');
			record.commit();
		}
	}
	/**
	 * Delayed task callback
	 */
	,requestProgress:function() {
		var records, p;
		var o = {
			 url:this.progressUrl
			,method:'post'
			,params:{}
			,scope:this
			,callback:function(options, success, response) {
				var o;
				if(true !== success) {
					return;
				}
				try {
					o = Ext.decode(response.responseText);
				}
				catch(e) {
					return;
				}
				if('object' !== Ext.type(o) || true !== o.success) {
					return;
				}

				if(this.singleUpload) {
					this.progress = {};
					for(p in o) {
						if(this.progressMap[p]) {
							this.progress[this.progressMap[p]] = parseInt(o[p], 10);
						}
					}
					if(true !== this.eventsSuspended) {
						this.fireEvent('progress', this, this.progress);
					}

				}
				else {
					for(p in o) {
						if(this.progressMap[p] && options.record) {
							options.record.set(this.progressMap[p], parseInt(o[p], 10));
						}
					}
					if(options.record) {
						options.record.commit();
						if(true !== this.eventsSuspended) {
							this.fireEvent('progress', this, options.record.data, options.record);
						}
					}
				}
				this.progressTask.delay(this.progressInterval);
			}
		};
		if(this.singleUpload) {
			o.params[this.progressIdName] = this.progressId;
			o.params.APC_UPLOAD_PROGRESS = this.progressId;
			Ext.Ajax.request(o);
		}
		else {
            var records = this.store.queryBy(function(r){return 'uploading' !== r.get('state');} );
			records.each(function(r) {
				o.params[this.progressIdName] = r.get('progressId');
				o.params.APC_UPLOAD_PROGRESS = o.params[this.progressIdName];
				o.record = r;
				Ext.Function.defer( (function() {
					Ext.Ajax.request(o);
				}), 250);
			}, this);
		}
	}
	/**
	 * path setter
	 * @private
	 */
	,setPath:function(path) {
		this.path = path;
	}
	/**
	 * url setter
	 * @private
	 */
	,setUrl:function(url) {
		this.url = url;
	}
	/**
	 * Starts progress fetching from server
	 * @private
	 */
	,startProgress:function() {
		if(!this.progressTask) {
			this.progressTask = new Ext.util.DelayedTask(this.requestProgress, this);
		}
		Ext.Function.defer( this.progressTask.delay, this.progressInterval / 2, this.progressTask, [this.progressInterval]);
	}
	/**
	 * Stops progress fetching from server
	 * @private
	 */
	,stopProgress:function() {
		if(this.progressTask) {
			this.progressTask.cancel();
		}
	}
	/**
	 * Stops all currently running uploads
	 */
	,stopAll:function() {
		var records = this.store.queryBy(function(r){return 'uploading' !== r.get('state');} );
		records.each(this.stopUpload, this);
	}
	/**
	 * Stops currently running upload
	 * @param {Ext.data.Record} record Optional, if not set singleUpload = true is assumed
	 * and the global stop is initiated
	 */
	,stopUpload:function(record) {
		// single abord
		var iframe = false;
		if(record) {
			iframe = this.getIframe(record);
			this.stopIframe(iframe);
			this.upCount--;
			this.upCount = 0 > this.upCount ? 0 : this.upCount;
			record.set('state', 'stopped');
			this.fireFinishEvents({record:record});
		}
		// all abort
		else if(this.form) {
			iframe = Ext.fly(this.form.dom.target);
			this.stopIframe(iframe);
			this.upCount = 0;
			this.fireFinishEvents();
		}

	}
	/**
	 * Stops uploading in hidden iframe
	 * @private
	 * @param {Ext.Element} iframe
	 */
	,stopIframe:function(iframe) {
		if(iframe) {
			try {
				iframe.dom.contentWindow.stop();
				Ext.Function.defer( iframe.remove, 250, iframe);
			}
			catch(e){}
		}
	}
	/**
	 * Main public interface function. Preforms the upload
	 */
	,upload:function() {

		var records = this.store.queryBy(function(r){return 'done' !== r.get('state');});
		if(!records.getCount()) {
			return;
		}

		// fire beforeallstart event
		if(true !== this.eventsSuspended && false === this.fireEvent('beforeallstart', this)) {
			return;
		}
		if(this.singleUpload) {
			this.uploadSingle();
		}
		else {
			records.each(this.uploadFile, this);
		}

		if(true === this.enableProgress) {
			this.startProgress();
		}

	}
	/**
	 * called for both success and failure. Does nearly nothing
	 * @private
	 * but dispatches processing to processSuccess and processFailure functions
	 */
	,uploadCallback:function(options, success, response) {

		var o;
		this.upCount--;
		this.form = false;

		// process ajax success
		if(true === success) {
			try {
				o = Ext.decode(response.responseText);
			}
			catch(e) {
				this.processFailure(options, response, this.jsonErrorText);
				this.fireFinishEvents(options);
				return;
			}
			// process command success
			if(true === o.success) {
				this.processSuccess(options, response, o);
			}
			// process command failure
			else {
				this.processFailure(options, response, o);
			}
		}
		// process ajax failure
		else {
			this.processFailure(options, response);
		}

		this.fireFinishEvents(options);

	}
	/**
	 * Uploads one file
	 * @param {Ext.data.Record} record
	 * @param {Object} params Optional. Additional params to use in request.
	 */
	,uploadFile:function(record, params) {
		// fire beforestart event
		if(true !== this.eventsSuspended && false === this.fireEvent('beforefilestart', this, record)) {
			return;
		}

		// create form for upload
		var form = this.createForm(record);

		// append input to the form
		var inp = record.get('input');
		inp.set({name:inp.id});
		form.appendChild(inp);

		// get params for request
		var o = this.getOptions(record, params);
		o.form = form;

		// set state
		record.set('state', 'uploading');
		record.set('pctComplete', 0);

		// increment active uploads count
		this.upCount++;

		// request upload
		Ext.Ajax.request(o);

		// todo:delete after devel
		Ext.Function.defer(this.getIframe, 100, this, [record]);

	}
	/**
	 * Uploads all files in single request
	 */
	,uploadSingle:function() {

		// get records to upload
		var records = this.store.queryBy(function(r){return 'done' !== r.get('state');});
		if(!records.getCount()) {
			return;
		}

		// create form and append inputs to it
		var form = this.createForm();
		records.each(function(record) {
			var inp = record.get('input');
			inp.set({name:inp.id});
			form.appendChild(inp);
			record.set('state', 'uploading');
		}, this);

		// create options for request
		var o = this.getOptions();
		o.form = form;

		// save form for stop
		this.form = form;

		// increment active uploads counter
		this.upCount++;

		// request upload
		Ext.Ajax.request(o);

	}
})

/**
 * Ext.ux.form.UploadPanel
 *
 * @license Ext.ux.form.UploadPanel is licensed under the terms of
 * the Open Source LGPL 3.0 license.  Commercial use is permitted to the extent
 * that the code/component(s) do NOT become part of another Open Source or Commercially
 * licensed development library or toolkit without explicit permission.
 *
 * License details: http://www.gnu.org/licenses/lgpl.html
 */
Ext.define('Ext.ux.model.UploadPanel', {
    extend: 'Ext.data.Model'
    ,fields:[
			 {name:'id', type:'text', system:true}
			,{name:'shortName', type:'text', system:true}
			,{name:'fileName', type:'text', system:true}
			,{name:'filePath', type:'text', system:true}
			,{name:'fileCls', type:'text', system:true}
			,{name:'input', system:true}
			,{name:'form', system:true}
			,{name:'state', type:'text', system:true}
			,{name:'error', type:'text', system:true}
			,{name:'progressId', type:'int', system:true}
			,{name:'bytesTotal', type:'int', system:true}
			,{name:'bytesUploaded', type:'int', system:true}
			,{name:'estSec', type:'int', system:true}
			,{name:'filesUploaded', type:'int', system:true}
			,{name:'speedAverage', type:'int', system:true}
			,{name:'speedLast', type:'int', system:true}
			,{name:'timeLast', type:'int', system:true}
			,{name:'timeStart', type:'int', system:true}
			,{name:'pctComplete', type:'int', system:true}
		]
});

Ext.define('Ext.ux.form.ExtendedUploadField', {
    alias:['widget.extendeduploadfield','extendeduploadfield']
    ,extend:'Ext.form.field.File'
    //overrides
    ,onRender : function(ct, position){
        Ext.ux.form.ExtendedUploadField.superclass.onRender.call(this, ct, position);

        this.wrap = this.el.wrap({cls:'x-form-field-wrap x-form-file-wrap'});
        this.el.addCls('x-form-file-text');
        this.el.dom.removeAttribute('name');
        this.fileInputEl = this.button.fileInputEl;
        this.resizeEl = this.positionEl = this.wrap;
    },
    //additional
    /**
        * Detaches the input file without clearing the value so that it can be used for
        * other purposes (e.g. uploading).
        *
        * The returned input file has all listeners applied to it by this class removed.
        * @return {Ext.Element} the detached input file element.
        */
    detachFileInput : function(){
        var result = this.button.fileInputEl;

        this.button.fileInputEl.removeAllListeners();
        this.button.fileInputEl = null;
        this.button.id = Ext.id(); //avoid dom conflicts
        this.button.createFileInput();
        this.fileInputEl = this.button.fileInputEl;
        return result;
    },

   /**
        * @return {Ext.Element} the input file element
        */
   getFileInput: function(){
       return this.fileInputEl;    //maybe return this.fileInputEl;
   }
});

/**
 * @class Ext.ux.UploadPanel
 * @extends Ext.panel.Panel
 */
Ext.define('Ext.ux.UploadPanel', {
    extend:'Ext.panel.Panel'
    ,alias:['uploadpanel','widget.uploadpanel']
	// configuration options overridable from outside
	/**
	 * @cfg {String} addIconCls icon class for add (file browse) button
	 */
	 ,addIconCls:'icon-plus'

	/**
	 * @cfg {String} addText Text on Add button
	 */
	,addText:'Add'

	/**
	 * @cfg {Object} baseParams This object is not used directly by FileTreePanel but it is
	 * propagated to lower level objects instead. Included here for convenience.
	 */

	/**
	 * @cfg {String} bodyStyle style to use for panel body
	 */
	,bodyStyle:'padding:2px'

	/**
	 * @cfg {String} clickRemoveText
	 */
	,clickRemoveText:'Click to remove'

	/**
	 * @cfg {String} clickStopText
	 */
	,clickStopText:'Click to stop'

	/**
	 * @cfg {String} emptyText empty text for dataview
	 */
	,emptyText:'No files'

	/**
	 * @cfg {Boolean} enableProgress true to enable querying server for progress information
	 * Passed to underlying uploader. Included here for convenience.
	 */
	,enableProgress:true

	/**
	 * @cfg {String} errorText
	 */
	,errorText:'Error'

	/**
	 * @cfg {String} fileCls class prefix to use for file type classes
	 */
	,fileCls:'file'

	/**
	 * @cfg {String} fileQueuedText File upload status text
	 */
	,fileQueuedText:'File <b>{0}</b> is queued for upload'

	/**
	 * @cfg {String} fileDoneText File upload status text
	 */
	,fileDoneText:'File <b>{0}</b> has been successfully uploaded'

	/**
	 * @cfg {String} fileFailedText File upload status text
	 */
	,fileFailedText:'File <b>{0}</b> failed to upload'

	/**
	 * @cfg {String} fileStoppedText File upload status text
	 */
	,fileStoppedText:'File <b>{0}</b> stopped by user'

	/**
	 * @cfg {String} fileUploadingText File upload status text
	 */
	,fileUploadingText:'Uploading file <b>{0}</b>'

	/**
	 * @cfg {Number} maxFileSize Maximum upload file size in bytes
	 * This config property is propagated down to uploader for convenience
	 */
	,maxFileSize:524288

	/**
	 * @cfg {Number} Maximum file name length for short file names
	 */
	,maxLength:18

	/**
	 * @cfg {String} removeAllIconCls iconClass to use for Remove All button (defaults to 'icon-cross'
	 */
	,removeAllIconCls:'icon-cross'

	/**
	 * @cfg {String} removeAllText text to use for Remove All button tooltip
	 */
	,removeAllText:'Remove All'

	/**
	 * @cfg {String} removeIconCls icon class to use for remove file icon
	 */
	,removeIconCls:'icon-minus'

	/**
	 * @cfg {String} removeText Remove text
	 */
	,removeText:'Remove'

	/**
	 * @cfg {String} selectedClass class for selected item of DataView
	 */
	,selectedClass:'ux-up-item-selected'

	/**
	 * @cfg {Boolean} singleUpload true to upload files in one form, false to upload one by one
	 * This config property is propagated down to uploader for convenience
	 */
	,singleUpload:false

	/**
	 * @cfg {String} stopAllText
	 */
	,stopAllText:'Stop All'

	/**
	 * @cfg {String} stopIconCls icon class to use for stop
	 */
	,stopIconCls:'icon-stop'

	/**
	 * @cfg {String} uploadText Upload text
	 */
	,uploadText:'Upload'

	/**
	 * @cfg {String} uploadIconCls icon class to use for upload button
	 */
	,uploadIconCls:'icon-upload'

	/**
	 * @cfg {String} workingIconCls iconClass to use for busy indicator
	 */
	,workingIconCls:'icon-working'

	/**
	 * @cfg {String} workingIconCls iconClass to use for busy indicator
	 */
	,inputsIds:'uploadFile'

	,initComponent:function() {
        var addCfg = {
            xtype:'extendeduploadfield'
            ,buttonOnly:true
            ,buttonText:this.addText + '...'
            ,buttonConfig:{
                iconCls:this.addIconCls
                ,baseCls:Ext.baseCSSPrefix + 'btn'
            }
            ,listeners: {
                change: {fn:this.onAddFile, scope:this}
            }
	    };

		// upload button configuration
		var upCfg = {
			 xtype:'button'
			,iconCls:this.uploadIconCls
			,text:this.uploadText
			,scope:this
			,handler:this.onUpload
			,disabled:true
		};

		// remove all button configuration
		var removeAllCfg = {
			 xtype:'button'
			,iconCls:this.removeAllIconCls
			,tooltip:this.removeAllText
			,scope:this
			,handler:this.onRemoveAllClick
			,disabled:true
		};

		// todo: either to cancel buttons in body or implement it
        this.tbar = [addCfg, upCfg, '->', removeAllCfg];

		// create store
		this.store = Ext.create('Ext.data.Store', {
			 id:0
			,model:'Ext.ux.model.UploadPanel'
			,data:[]
			,proxy:{type:'memory'}
		});

		// create view
		Ext.apply(this, {
			items:[{
				 xtype:'dataview'
				,store:this.store
				,singleSelect:true
                ,tpl: this.tpl || new Ext.XTemplate(
                      '<tpl for=".">'
                    + '<div class="ux-up-item">'
                    + '<div class="ux-up-icon-file {fileCls}">&#160;</div>'
                    + '<div class="ux-up-text x-unselectable" data-qtip="{fileName}">{shortName}</div>'
                    + '<div id="remove-{[values.input.id]}" class="ux-up-icon-state ux-up-icon-{state}"'
                    + 'data-qtip="{[this.scope.getQtip(values)]}">&#160;</div>'
                    + '</div>'
                    + '</tpl>'
                    , {scope:this}
                )
                ,itemSelector:'div.ux-up-item'
                ,emptyText:this.emptyText
                ,itemTpl: this.tpl || new Ext.XTemplate(
                      '<tpl for=".">'
                    + '<div class="ux-up-item">'
                    + '<div class="ux-up-icon-file {fileCls}">&#160;</div>'
                    + '<div class="ux-up-text x-unselectable" data-qtip="{fileName}">{shortName}</div>'
                    + '<div id="remove-{[values.input.id]}" class="ux-up-icon-state ux-up-icon-{state}"'
                    + 'data-qtip="{[this.scope.getQtip(values)]}">&#160;</div>'
                    + '</div>'
                    + '</tpl>'
                    , {scope:this}
                )
				,listeners:{
                    itemclick:{scope:this, fn:this.onViewClick}
                }
			}]
		});

		// call parent
		Ext.ux.UploadPanel.superclass.initComponent.apply(this, arguments);

		// save useful references
		this.view = this.items.getAt(0);

		// add events
		this.addEvents(
			/**
			 * Fires before the file is added to store. Return false to cancel the add
			 * @event beforefileadd
			 * @param {Ext.ux.UploadPanel} this
			 * @param {Ext.Element} input (type=file) being added
			 */
			'beforefileadd'
			/**
			 * Fires after the file is added to the store
			 * @event fileadd
			 * @param {Ext.ux.UploadPanel} this
			 * @param {Ext.data.Store} store
			 * @param {Ext.data.Record} Record (containing the input) that has been added to the store
			 */
			,'fileadd'
			/**
			 * Fires before the file is removed from the store. Return false to cancel the remove
			 * @event beforefileremove
			 * @param {Ext.ux.UploadPanel} this
			 * @param {Ext.data.Store} store
			 * @param {Ext.data.Record} Record (containing the input) that is being removed from the store
			 */
			,'beforefileremove'
			/**
			 * Fires after the record (file) has been removed from the store
			 * @event fileremove
			 * @param {Ext.ux.UploadPanel} this
			 * @param {Ext.data.Store} store
			 */
			,'fileremove'
			/**
			 * Fires before all files are removed from the store (queue). Return false to cancel the clear.
			 * Events for individual files being removed are suspended while clearing the queue.
			 * @event beforequeueclear
			 * @param {Ext.ux.UploadPanel} this
			 * @param {Ext.data.Store} store
			 */
			,'beforequeueclear'
			/**
			 * Fires after the store (queue) has been cleared
			 * Events for individual files being removed are suspended while clearing the queue.
			 * @event queueclear
			 * @param {Ext.ux.UploadPanel} this
			 * @param {Ext.data.Store} store
			 */
			,'queueclear'
			/**
			 * Fires after the upload button is clicked but before any upload is started
			 * Return false to cancel the event
			 * @param {Ext.ux.UploadPanel} this
			 */
			,'beforeupload'
		);
		// relay view events
		this.relayEvents(this.view, [
			 'beforeclick'
			,'beforeselect'
			,'click'
			,'containerclick'
			,'contextmenu'
			,'dblclick'
			,'selectionchange'
		]);
		// }}}

		// create uploader
		var config = {
			 store:this.store
			,singleUpload:this.singleUpload
			,maxFileSize:this.maxFileSize
			,enableProgress:this.enableProgress
			,url:this.url
			,path:this.path
		};
		if(this.baseParams) {
			config.baseParams = this.baseParams;
		}
		this.uploader = Ext.create('Ext.ux.FileUploader', config);

		// relay uploader events
		this.relayEvents(this.uploader, [
			 'beforeallstart'
			,'allfinished'
			,'progress'
		]);

		// install event handlers
		this.on({
			 beforeallstart:{scope:this, fn:function() {
			 	this.uploading = true;
				this.updateButtons();
			}}
			,allfinished:{scope:this, fn:function() {
				this.uploading = false;
				this.updateButtons();
			}}
			,progress:{fn:Ext.Function.bind( this.onProgress, this)}
		});
	}
	/**
	 * onRender override, saves references to buttons
	 * @private
	 */
	,onRender:function() {
		// call parent
		Ext.ux.UploadPanel.superclass.onRender.apply(this, arguments);

		// save useful references
		var tb = this.getDockedItems();
		this.addBtn = Ext.getCmp(tb[0].items.first().id);
		this.uploadBtn = Ext.getCmp(tb[0].items.getAt(1).id);
		this.removeAllBtn = Ext.getCmp(tb[0].items.last().id);
	}

	// added methods
	/**
	 * called by XTemplate to get qtip depending on state
	 * @private
	 * @param {Object} values XTemplate values
	 */
	,getQtip:function(values) {
		var qtip = '';
		switch(values.state) {
			case 'queued':
				qtip = Ext.String.format(this.fileQueuedText, values.fileName);
				qtip += '<br>' + this.clickRemoveText;
			break;

			case 'uploading':
				qtip = Ext.String.format(this.fileUploadingText, values.fileName);
				qtip += '<br>' + values.pctComplete + '% done';
				qtip += '<br>' + this.clickStopText;
			break;

			case 'done':
				qtip = Ext.String.format(this.fileDoneText, values.fileName);
				qtip += '<br>' + this.clickRemoveText;
			break;

			case 'failed':
				qtip = Ext.String.format(this.fileFailedText, values.fileName);
				qtip += '<br>' + this.errorText + ':' + values.error;
				qtip += '<br>' + this.clickRemoveText;
			break;

			case 'stopped':
				qtip = Ext.String.format(this.fileStoppedText, values.fileName);
				qtip += '<br>' + this.clickRemoveText;
			break;
		}
		return qtip;
	} 
	/**
	 * get file name
	 * @private
	 * @param {Ext.Element} inp Input element containing the full file path
	 * @return {String}
	 */
	,getFileName:function(inp) {
		return inp.getValue().split(/[\/\\]/).pop();
	} // eo function getFileName
	/**
	 * get file path (excluding the file name)
	 * @private
	 * @param {Ext.Element} inp Input element containing the full file path
	 * @return {String}
	 */
	,getFilePath:function(inp) {
		return inp.getValue().replace(/[^\/\\]+$/,'');
	}
	/**
	 * returns file class based on name extension
	 * @private
	 * @param {String} name File name to get class of
	 * @return {String} class to use for file type icon
	 */
	,getFileCls: function(name) {
		var atmp = name.split('.');
		if(1 === atmp.length) {
			return this.fileCls;
		}
		else {
			return this.fileCls + '-' + atmp.pop().toLowerCase();
		}
	}
	/**
	 * called when file is added - adds file to store
	 * @private
        * @param {Ext.form.field.File} fu
        * @param {String} fileName
	 */
    ,onAddFile:function(field,fileName, options) {
        var inp = field.button.fileInputEl;
		if(true !== this.eventsSuspended && false === this.fireEvent('beforefileadd', this, inp))
        {
			return;
		}
        field.detachFileInput();
        var recId = inp.id;
        if(this.inputsIds)inp.id=this.inputsIds;

		// create Ext.create('record and add it to store
		var rec = new this.store.model({
			 input:inp
			,fileName:fileName
			,filePath:this.getFilePath(inp)
			,shortName: Ext.util.Format.ellipsis(fileName, this.maxLength)
			,fileCls:this.getFileCls(fileName)
			,state:'queued'
		}, recId);
		rec.commit();
		this.store.add(rec);

		this.syncShadow();

		this.uploadBtn.enable();
		this.removeAllBtn.enable();

		if(true !== this.eventsSuspended) {
			this.fireEvent('fileadd', this, this.store, rec);
		}
		this.doLayout();

	} 
	/**
	 * destroys child components
	 * @private
	 */
	,onDestroy:function() {

		// destroy uploader
		if(this.uploader) {
//			this.uploader.stopAll();
			this.uploader.clearListeners();
			this.uploader = null;
		}

		// destroy view
		if(this.view) {
			this.view.clearListeners();
			this.view.destroy();
			this.view = null;
		}

		// destroy store
		if(this.store) {
			this.store.clearListeners();
			this.store.destroy();
			this.store = null;
		}

	} 
	/**
	 * progress event handler
	 * @private
	 * @param {Ext.ux.FileUploader} uploader
	 * @param {Object} data progress data
	 * @param {Ext.data.Record} record
	 */
	,onProgress:function(uploader, data, record) {
		var bytesTotal, bytesUploaded, pctComplete, state, idx, item, width, pgWidth;
		if(record) {
			state = record.get('state');
			bytesTotal = record.get('bytesTotal') || 1;
			bytesUploaded = record.get('bytesUploaded') || 0;
			if('uploading' === state) {
				pctComplete = Math.round(1000 * bytesUploaded/bytesTotal) / 10;
			}
			else if('done' === state) {
				pctComplete = 100;
			}
			else {
				pctComplete = 0;
			}
			record.set('pctComplete', pctComplete);

			idx = this.store.indexOf(record);
			item = Ext.get(this.view.getNode(idx));
			if(item) {
				width = item.getWidth();
				item.applyStyles({'background-position':width * pctComplete / 100 + 'px'});
			}
		}
	}
	/**
	 * called when file remove icon is clicked - performs the remove
	 * @private
	 * @param {Ext.data.Record}
	 */
	,onRemoveFile:function(record) {
		if(true !== this.eventsSuspended && false === this.fireEvent('beforefileremove', this, this.store, record)){
			return;
		}

		// remove DOM elements
		var inp = record.get('input');
        if(inp){
            var wrap = inp.up('em');
            inp.remove();
            if(wrap) {
                wrap.remove();
            }
        }
		// remove record from store
		this.store.remove(record);

		var count = this.store.getCount();
		this.uploadBtn.setDisabled(!count);
		this.removeAllBtn.setDisabled(!count);

		if(true !== this.eventsSuspended) {
			this.fireEvent('fileremove', this, this.store);
			this.syncShadow();
		}
	}
	/**
	 * Remove All/Stop All button click handler
	 * @private
	 */
	,onRemoveAllClick:function(btn) {
		if(true === this.uploading) {
			this.stopAll();
		}
		else {
			this.removeAll();
		}
	}

	,stopAll:function() {
		this.uploader.stopAll();
	}
	/**
	 * DataView click handler
	 * @private
	 */
	,onViewClick:function(view, record, node, index, e, options) {
		var t = e.getTarget('div:any(.ux-up-icon-queued|.ux-up-icon-failed|.ux-up-icon-done|.ux-up-icon-stopped)');
		if(t) {
			this.onRemoveFile(this.store.getAt(index));
		}
		t = e.getTarget('div.ux-up-icon-uploading');
		if(t) {
			this.uploader.stopUpload(this.store.getAt(index));
		}
        t = e.getTarget('div.ux-up-text');
        if(t) {
            if(node.lastChild.className.indexOf('ux-up-icon-done')!=-1) this.fireEvent('nameClicked',index);
        }
        return false;
	}
	/**
	 * tells uploader to upload
	 * @private
	 */
	,onUpload:function() {
		if(true !== this.eventsSuspended && false === this.fireEvent('beforeupload', this)) {
			return false;
		}
		this.uploader.upload();
	}
	/**
	 * url setter
	 */
	,setUrl:function(url) {
		this.url = url;
		this.uploader.setUrl(url);
	}
	/**
	 * path setter
	 */
	,setPath:function(path) {
		this.uploader.setPath(path);
	}
	/**
	 * Updates buttons states depending on uploading state
	 * @private
	 */
	,updateButtons:function() {
		if(true === this.uploading) {
			this.addBtn.disable();
			this.uploadBtn.disable();
			this.removeAllBtn.setIconCls(this.stopIconCls);
			this.removeAllBtn.tooltip = this.stopAllText;
		}
		else {
			this.addBtn.enable();
			this.uploadBtn.enable();
			this.removeAllBtn.setIconCls(this.removeAllIconCls);
			this.removeAllBtn.tooltip = this.removeAllText;
		}
	}
	/**
	 * Removes all files from store and destroys file inputs
	 */
	,removeAll:function() {
		var suspendState = this.eventsSuspended;
		if(false !== this.eventsSuspended && false === this.fireEvent('beforequeueclear', this, this.store)) {
			return false;
		}
		this.suspendEvents();

        for (var i = this.store.getCount()-1; i > -1; i--) {
            this.onRemoveFile.call(this, this.store.getAt(i));
        }
//        this.store.each(this.onRemoveFile, this);

		this.eventsSuspended = suspendState;
		if(true !== this.eventsSuspended) {
			this.fireEvent('queueclear', this, this.store);
		}
		this.syncShadow();
	}
    /**
	 * synchronize context menu shadow if we're in contextmenu
	 * @private
	 */
	,syncShadow:function() {
		if(this.contextmenu && this.contextmenu.shadow) {
			this.contextmenu.getEl().shadow.show(this.contextmenu.getEl());
            this.ownerCt.doLayout();
		}
	}
});