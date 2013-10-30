var offset = 0;
var max = 15;

function Song(data) {
    this.artist = ko.observable(data.artist);
    this.title = ko.observable(data.title);
}

function SongsViewModel() {
    // Data
    var self = this;

    self.voornaam = ko.observable("bert");
    self.songs = ko.observableArray([]);
    self.findSongs = ko.observableArray([]);

    self.newSongText = ko.observable();
    self.url = ko.observable();

    url = "http://localhost:8080/voornaaminliedje/api/songs/some?offset=" + offset + "&max=" + max;
    urlMax = "http://localhost:8080/voornaaminliedje/api/songs/max";
    count = 0;

    urlFind = "http://localhost:8080/voornaaminliedje/api/song/find/";
    +self.voornaam();

    $.getJSON(urlMax, function(data) {
        count = data.valueOf();
    });

    // Load initial state from server, convert it to Song instances, then populate self.songs
    $.getJSON(url, function(allData) {
        var mappedSongs = $.map(allData, function(item) {
            return new Song(item);
        });
        self.songs(mappedSongs);
    });

    // Load find from server, convert it to Song instances, then populate self.songs
    $.getJSON(urlFind + self.voornaam(), function(allData) {
        var mappedSongs = $.map(allData, function(item) {
            return new Song(item);
        });
        self.findSongs(mappedSongs);
    });

    self.previous = function() {
        if (offset > max) {
            offset = offset - max;
        } else {
            offset = 0;
        }

        url = "http://localhost:8080/voornaaminliedje/api/songs/some?offset=" + offset + "&max=" + max;
        $.getJSON(url, function(allData) {
            var mappedSongs = $.map(allData, function(item) {
                return new Song(item);
            });
            self.songs(mappedSongs);
        });
    };

    self.next = function() {
        if (offset + 2 * max < count) {
            offset = offset + max;
        } else {
            offset = count - max;
        }
        url = "http://localhost:8080/voornaaminliedje/api/songs/some?offset=" + offset + "&max=" + max;
        $.getJSON(url, function(allData) {
            var mappedSongs = $.map(allData, function(item) {
                return new Song(item);
            });
            self.songs(mappedSongs);
        });
    };

    self.zoeknummers = function() {
        $.getJSON(urlFind + self.voornaam(), function(allData) {
            var mappedSongs = $.map(allData, function(item) {
                return new Song(item);
            });
            self.findSongs(mappedSongs);
        });
    };
}

ko.applyBindings(new SongsViewModel());