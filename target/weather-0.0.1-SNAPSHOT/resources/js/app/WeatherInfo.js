Ext.define('Expedia.weather.WeatherInfo', {
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
});