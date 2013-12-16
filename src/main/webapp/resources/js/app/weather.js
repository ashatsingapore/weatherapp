Ext.require([
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