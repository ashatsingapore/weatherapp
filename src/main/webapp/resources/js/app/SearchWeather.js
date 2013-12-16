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
});