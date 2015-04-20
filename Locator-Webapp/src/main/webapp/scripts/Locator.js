var Locator = {

    zoom: 18,
    map: {},
    points: [],

    addLocation: function(lat, lon){
        var fromProjection = new OpenLayers.Projection("EPSG:4326");   // Transform from WGS 1984
        var toProjection   = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
        var position       = new OpenLayers.LonLat(lon, lat).transform( fromProjection, toProjection);
        Locator.map = new OpenLayers.Map("Map");
        var mapnik         = new OpenLayers.Layer.OSM();
        Locator.map.addLayer(mapnik);

        var markers = new OpenLayers.Layer.Markers( "Markers" );
        Locator.map.addLayer(markers);
        markers.addMarker(new OpenLayers.Marker(position));

        Locator.map.setCenter(position, Locator.zoom);
    },

    drawLine: function() {
       var lineLayer = new OpenLayers.Layer.Vector("Line Layer");
       Locator.map.addLayer(lineLayer);
       var lineGeom = new OpenLayers.Geometry.LineString(points);
       var lineFeat = new OpenLayers.Feature.Vector(lineGeom);
       lineLayer.addFeatures([lineFeat]);
    },

    addPoint: function(long, lat) {
        Locator.points.push(new OpenLayers.Geometry.Point(long, lat));
    }
};

jQuery(document).ready(function() {
   jQuery.ajax("/json?user=test").done(function(data) {
       var jsonObj = JSON.parse(data);
       jQuery.each(jsonObj, function(lon, lat) {
        Locator.addPoint(lon, lat);
       });
   });
});