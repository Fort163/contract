Reimbursement = {
    LOADING_MESSAGE: 'Загрузка данных...'
//    ,WORK_DIV:'zags-work-div'
    , LOGOUT_ACTION: '/logout'
    , BODY_DIV: Ext.getBody()
    /**
     * Puts a mask over the elDom element to disable user interaction.
     * @param {Boolean} isShow show or hide the mask
     * @param {String} elDom (optional) dom element to mask or unmask. if not specified the BODY_DIV will be masked or unmasked
     */
    , wait: function (isShow, elDom) {
        var el = Ext.isElement(elDom) ? elDom : (Ext.isString(elDom) && Ext.fly(elDom)) ? Ext.fly(elDom) : Ext.getBody();
        if (isShow) el.mask(Reimbursement.LOADING_MESSAGE, 'x-mask-loading');
        else el.unmask();
    }
    /**
     * Masks the BODY_DIV
     */
    , waitOn: function () {
        this.wait(true);
    }
    /**
     * Unmasks the BODY_DIV
     */
    , waitOff: function () {
        this.wait(false);
    }
    /**
     * Updates div sending Ajax request to a server
     * @param opts An object which may contain all Ext.Ajax.request() properties and these properties:
     * url : String The URL to which to send the request
     * maskEl : Boolean/String (Optional) do show body mask while updating or DOM id to show mask on
     * updEl : String (Optional) which element update (DOM id)
     * clbFn : Function (Optional) callback function
     */
    /*    ,updateDiv:function(opts){
     Ext.applyIf(opts,{
     maskEl:false
     ,url:false
     ,updEl:false
     ,clbFn:Ext.emptyFn
     });
     if(!opts.url) return false;
     var me=this;
     if(opts.maskEl)me.wait(true,opts.maskEl);
     Ext.apply(opts,{
     callback:function(o,s,r){
     if(o.maskEl)me.wait(false,o.maskEl);
     if(!s){
     switch(r.status){
     case 403:{
     document.location.replace(me.LOGOUT_ACTION);
     break;
     }
     default:alertTextError(r);
     }
     }
     else if(r.responseText.indexOf('<html>')!=-1)
     {
     document.location.replace(me.LOGOUT_ACTION);
     }
     else{
     var el=o.updEl ? o.updEl : me.WORK_DIV;
     Ext.fly(el).update(r.responseText,true);
     o.clbFn();
     }
     }
     });
     Ext.Ajax.request(opts);
     }*/
    /**
     * Sends ordinar HTTP request to server
     * @param opts An object which may contain the following properties:
     * action : String The URL to which to send the request
     * method : String (Optional) HTTP method (POST by default)
     * target : String (Optional) HTTP form target. if target is 'here' then response comes to hidden iframe
     * remove : Boolean (Optional) do remove the form after it is submited
     * delay  : Number (Optional) delay (in miliseconds) before form removing (if remove==true)
     */
    , submitForm: function (opts) {
        Ext.applyIf(opts, {
            tag: 'form'
            , action: ''
            , method: 'post'
            , cls: 'x-hidden'
            , id: Ext.id()
            , remove: false
            , delay: 3000
        });
        if (Ext.isObject(opts.params)) {
            if (!Ext.isArray(opts.cn)) {
                opts.cn = [];
            }
            for (var prop in opts.params) {
                if (prop == "prop") {
                    var str = opts.params[prop];
                    if (str) {
                        var arr1 = str.split("&");
                        for (var i = 0; i < arr1.length; i++) {
                            var par = arr1[i];
                            var arr2 = par.split("=");
                            var v1 = arr2[0];
                            var v2 = arr2[1];
                            opts.cn.push({
                                tag: 'input'
                                , type: 'hidden'
                                , name: v1
                                , id: v1
                                , value: v2
                            })
                        }
                    }
                }
                else {
                    if (Ext.isArray(opts.params[prop]))
                    {
                        for (var i = 0; i < opts.params[prop].length; i++) {

                            opts.cn.push({
                                tag: 'input'
                                , type: 'hidden'
                                , name: prop
                                , id: prop
                                , value: opts.params[prop][i]
                            });
                        }
                    }
                    else {
                        opts.cn.push({
                            tag: 'input'
                            , type: 'hidden'
                            , name: prop
                            , id: prop
                            , value: opts.params[prop]
                        });
                    }
                }
            }
        }
        var button = opts.button ? opts.button : false;
        if (button) button.disable();
        var form = Ext.getBody().createChild(opts);
        /*form.addEvents('submiting');*/
        if (opts.target && opts.target == 'here') {
            if (!Ext.get(opts.target)) {
                Ext.DomHelper.append(form, {
                    tag: 'iframe'
                    , id: opts.target
                })
            }
        }
        form.dom.submit();
        if (opts.remove) {
            var printCallback = function (frm, btn) {
                frm.remove();
                if (btn) btn.enable();
                if (opts.submiting) opts.submiting()
            };
            var task = new Ext.util.DelayedTask(printCallback, this, [form, button]);
            task.delay(opts.delay);
        }
    }
    /*
     ,deadSession:function(val){
     if(Ext.isObject(val)&&Ext.isEmpty(val.responseText))val=val.responseText;
     if(Ext.isString(val)&&val.indexOf('<html>')!=-1){
     document.location.replace(this.LOGOUT_ACTION);
     return true;
     }
     return false;
     }
     */
    , ieDalayedTask: new Ext.util.DelayedTask(function () {
    })
    , limitInput: function (dom, limit) {
        var value = dom.value;
        if (value.length < limit)return true;
        dom.value = value.substr(0, limit);
    }
    , trueInput: function (name) {
        var val = Ext.getDom(name).value;
        if (Ext.isBoolean(val)) return val;
        if (Ext.isString(val)) return val.toLowerCase().trim() == 'true';
        return false;
    }
    , showPopUp: function (title, text) {
        Ext.MessageBox.show({
            title: title
            , msg: text
            , width: 350
            , buttons: Ext.MessageBox.OK
        });
    }
    , debug: {
        console: {
            /**
             * show debug console
             */
            show: function () {
                var console = Ext.get(this.CONSDIV);
                if (!console) {
                    console = Ext.getBody().insertFirst({
                        tag: 'div'
                        , cls: 'mrnConsole'
                        , id: this.CONSDIV
                    })
                }
                console.show();
                return console;
            }
            , hide: function () {
                var console = Ext.get(this.CONSDIV);
                if (console) console.hide();
            }
            , destroy: function () {
                var console = Ext.get(this.CONSDIV);
                if (console) console.destroy();
            }
            /**
             * write to console
             * @param {Object | String} obj what display in console
             */
            , append: function (obj, name) {
                var console = this.show();
                if (console) {
                    var str = new Date().format('H:i:s.u') + '>>';
                    if (name) str += name + ': ';
                    if (Ext.isObject(obj)) {
                        str = '{';
                        for (var prop in obj) {
                            str += prop + ':' + obj[prop] + ',';
                        }
                        str = str.substr(0, str.length - 1);
                        str += '}';
                    }
                    else if (Ext.isArray(obj)) str = obj.toString();
                    else str += obj;
                    console.insertFirst({
                        tag: 'div'
                        , html: str
                    });
                }
            }
            , CONSDIV: 'console-div'
        }
        /**
         * attaches display-event-listeners to all objects events
         */
        , listenEvents: function (obj, name) {
            var cpfEv = obj.events;
            if (!name) name = 'observed';
            var firestarter = function (o, eve) {
                o.on(eve, function (a, b, c, d, e, f, g) {
                    Reimbursement.debug.console.append(eve, name);
                })
            };
            for (prop in cpfEv) {
                firestarter(obj, prop);
            }
        }
    }
};
Reimbursement.formSubmitter = Reimbursement.submitForm;


Ext.define('CMS.view.FileDownload', {
    extend: 'Ext.Component',
    alias: 'widget.FileDownloader',
    autoEl: {
        tag: 'iframe',
        cls: 'x-hidden',
        src: Ext.SSL_SECURE_URL
    },
    stateful: false,
    load: function(config){
        var e = this.getEl();
        e.dom.src = config.url + (config.params ? '?' + Ext.urlEncode(config.params) : '');
        e.dom.onload = function(){
            if(e.dom.contentDocument.body.childNodes[0].wholeText == '404') {
                Ext.Msg.show({
                    title: 'Attachment missing',
                    msg: 'The document you are after can not be found on the server.',
                    buttons: Ext.Msg.OK,
                    icon: Ext.MessageBox.ERROR
                })
            }
        }
    }
});


