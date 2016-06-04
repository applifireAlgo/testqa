Ext.define('Testone.view.databrowsercalendar.DBCalendar', {
	extend : 'Testone.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Testone.view.databrowsercalendar.DBCalendarController',
	             'Testone.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
