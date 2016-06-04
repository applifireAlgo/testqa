Ext.define('Testone.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Testone.view.appreportui.ReportViewController',
	            'Testone.view.appreportui.datagrid.DataGridPanel',
	            'Testone.view.appreportui.datagrid.DataGridView',
	            'Testone.view.appreportui.querycriteria.QueryCriteriaView',
	            'Testone.view.appreportui.chart.ChartView',
	            'Testone.view.appreportui.datapoint.DataPointView',
	            'Testone.view.googlemaps.map.MapPanel',
	            'Testone.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
