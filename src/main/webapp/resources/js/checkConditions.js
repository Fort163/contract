var lastClick = false;


function showMessage(visible)
{
    var ct=document.all ? document.all["blockLinkMessage"] : document.getElementById("blockLinkMessage");
    if(!visible)
    {
        ct.style.display = "none";
    }
    else
    {
        ct.style.display = "";
        xStart = (document.all) ? document.body.scrollLeft : window.pageXOffset;
        yStart = (document.all) ? document.body.scrollTop : window.pageYOffset;
        ct.style.top = yStart+Math.round(document.body.clientHeight/2)-(100/2);
        ct.style.left = xStart+Math.round(document.body.clientWidth/2)-(300/2);
    }
    return;
}

function hideData()
{
    showWindow( true );
    if( Ext.Ajax.abortAll ) Ext.Ajax.abortAll();
    return true;
}

function showAllSelects(show)
{
    var value = show?'visible':'hidden';
    var arr = document.getElementsByTagName('select');
    for(var i = 0; i < arr.length; i++ )
    {
        arr[i].style.visibility=value;
    }
}

var beforeShowBackDrop = function () { showAllSelects(false); };
var beforeHideBackDrop = function () { showAllSelects(true); };
function showWindow( isVisible ){
    if( Ext )
    {
        var el = Ext.getBody?Ext.getBody():Ext.get(document.body);
        if(isVisible) el.mask('Загрузка данных...','x-mask-loading');
        else el.unmask();
    }
}

function isEmpty( data )
{
    var empty = true;
    for( i = 0; i < data.length; i++ )
    {
        if( data.charAt(i) != " " )
        {
            empty = false;
            break;
        }
    }
    return empty;
}

function trim(s)
{
    return s.replace(/^\s*/, "").replace(/\s*$/, "");
}

function trimDigits( s )
{
    return s.replace(/\D*/, "");
}

function trimSpace( s )
{
    return s.replace(/\s*/, "");
}

function check( input )
{
    var ok = true;

    for( var i = 0; i < input.length; i++ )
    {
        var chr = input.charAt(i);
        var found = false;
        for( var j = 1; j < check.length; j++ )
        {
            if( chr == check[j] ) found = true;
        }
        if( !found ) ok = false;
    }

    return ok;
}

function checkInteger( input )
{
    var ok = true;
    var cnt = 0;

    var checks = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0"];
    for( var i = 0; i < input.length; i++ )
    {
        var chr = input.charAt(i);
        var found = false;
        for( var j = 0; j < checks.length; j++ )
        {
            if( chr == checks[j] ) found = true;
        }
        if( !found ) ok = false;
    }
    return ok;
}

/**
 * dd.MM.yyyy parses to Date value
 */

var parseStringToDate = function (tmpdate)
{
    if( Ext )
    {
        if( Ext.versions )
        {
            if( Ext.versions.core.major = '4') return Ext.Date.parseDate(tmpdate,'d.m.Y');
        }
        if(!Ext.version ||Ext.version == '3.4.0') return new Date.parseDate(tmpdate,'d.m.Y');
        return Ext.Date.parseDate(tmpdate,'d.m.Y');
    }
};


/**
 *  reduce text field value to the lower-cased string starting with capital letter
 */

var makeNameField=function(obj)
{
    obj.value=makeNameString(obj.value);
};

/**
 * reduce string to the lower-cased string starting with capital letter
 */

var makeNameString=function(str){
    if(trim(str)=='') return '';
    str.replace(/^\s+/,"");
    str.replace(/((\s*\S+)*)\s*/, "$1");
    var res = str.substr(0,1).toUpperCase()+str.substr(1).toLowerCase();
    if(res.indexOf('-')!=-1)
    {
        var arr=res.split('-');
        res=arr[0];
        for(var i=1;i<arr.length;i++)
        {
            res+='-'+arr[i].substr(0,1).toUpperCase()+arr[i].substr(1);
        }
    }
    if(res.indexOf(' ')!=-1)
    {
        arr=res.split(' ');
        res=arr[0];
        for(i=1;i<arr.length;i++)
        {
            res+=' '+arr[i].substr(0,1).toUpperCase()+arr[i].substr(1);
        }
    }
    return res;
};

var dateValidator = function(startDate, endDate)
{
    return parseStringToDate(endDate) - parseStringToDate(startDate) >= 0;
};

var checkDouble = function(digit)
{
    var pattern = /^\d+.?\d*$/;
    return digit.match(pattern) != null;
};

var checkInputFieldDoubleRange = function(e, idInputField)
{
    var key = (typeof e.charCode == 'undefined' ? e.keyCode : e.charCode);
    if (!(key==0 || key == 46 || (key>=48 && key<=57))) return false;
    var pattern = /^\d+.?\d*$/;
    var dig = key - 48;
    if (key == 46) dig='.';
    var digit = Ext.getDom(idInputField).value+dig;
    return digit.match(pattern) != null;
};

//Check input values
var checkinputFieldIntRange = function(e, idInputField)
{
    var key = (typeof e.charCode == 'undefined' ? e.keyCode : e.charCode);
    if (!(key==0 || (key>=48 && key<=57))) return false;
    var dig = key - 48;
    if (Ext.getDom(idInputField).value+dig >2147483647) return false;
    return true;
};

checkInputFieldInt = function(e)
{
    var key = (typeof e.charCode == 'undefined' ? e.keyCode : e.charCode);
    return key==0 || (key>=48 && key<=57);
};

checkInputFieldSnils = function(e)
{
    var key = (typeof e.charCode == 'undefined' ? e.keyCode : e.charCode);
    return key==0 || key == 45 || (key>=48 && key<=57);
};

checkInputFieldDouble = function(e)
{
    var key = (typeof e.charCode == 'undefined' ? e.keyCode : e.charCode);
    return key==0 || key == 46 || key == 32 || (key>=48 && key<=57);
};
function alertError(r){
    alert('Error '+r.status+': '+r.statusText);
}