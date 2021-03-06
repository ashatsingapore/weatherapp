Ext.define('Expedia.weather.SearchWeather', {
    extend: 'Ext.form.Panel',
    alias: 'widget.expedia.searchweather',
    layout: 'anchor',
    cls: 'search-form',
    config:{
        data:[],
        weatherDisplayData:[],
        weatherRenderer:''
    },
    constructor: function(config) {
        this.initConfig(config);
        this.callParent(arguments);
    },
    initComponent: function(config){
            this.callParent(arguments);
            this.on({
               scope: this
            });
    },
    items: [{
                xtype: 'fieldset',
                title: 'Search by Zip',
                defaultType: 'textfield',
                defaults: {
                    width: 280
                },
                items: [{
                        fieldLabel: 'Zip Code',
                        emptyText: 'Zip Code',
                        name: 'zipCode'
                    }
                ]
    }],
    buttons: [{
        text: 'Get Weather',
        disabled: true,
        formBind: true,
        handler: function(btn){
            var formPnl = btn.up('panel');
            var form = formPnl.getForm();
            var zipCode = form.findField('zipCode').getSubmitValue();
            Ext.Ajax.request({ url: 'zipcode.json',
                params: {'zipCode': zipCode},
                jsonData: { },
                method:'POST',
                success: function(response, opts) {
                    resp =  eval('('+response.responseText+')');
                    Ext.fly('error-message-div').hide();
                    Ext.fly('weather-widget').show();
                    Ext.fly('weather-widget').update('');
                    Ext.create('Expedia.weather.WeatherInfo',{
                            data:resp.msg,
                            renderTo: 'weather-widget'
                    });
                },
                failure:function(response, opt) {
                    resp =  eval('('+response.responseText+')');
                    Ext.fly('weather-widget').hide();
                    Ext.fly('error-message-div').show();
                    Ext.fly('error-message').update(resp.msg);
                }
            });
        }
    }]
});Ext.define('Expedia.weather.WeatherInfo', {
    extend: 'Ext.Component',
    alias: 'widget.expedia.weatherinfo',
    layout: 'anchor',
    cls: 'weather-view',
    config:{
        data:[]
    },
    constructor: function(config) {
        this.initConfig(config);
        this.callParent(arguments);
    },
    initComponent: function(config){
        var me = this;
        var weatherView = {html: this.weatherTpl()};
        Ext.applyIf(this,weatherView);
        this.callParent(arguments);
    },
    weatherTpl: function(){
        var me = this;
        data = this.getData();
        tpl = new Ext.XTemplate(
            '<div name="weather-display-div" class="info-outer">'+
                '<span class="info-inner">City : {city:trim} <br/> State : {state} <br/> Temperature : {temperature}</span>'+
            '</div>'
        ).applyTemplate(data);

        return tpl;
    }
});Ext.require([
    'Ext.form.*',
    'Ext.data.*',
    'Expedia.weather.*'
]);

Ext.onReady(function(){
    var formPanel = Ext.create('Expedia.weather.SearchWeather',{
            renderTo: 'weather-form',
            frame: true,
            title:'Weather Search',
            width: 340,
            bodyPadding: 5,
            waitMsgTarget: true,
            fieldDefaults: {
                labelAlign: 'right',
                labelWidth: 85,
                msgTarget: 'side'
            }
    });
});