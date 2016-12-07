/**
 * Created by Александр on 07.11.2016.
 */
var AdvertService = (function () {

    var methods = {
        loadAdvert: function (advertId, async, callback, errorback) {
            $.ajax({
                url: "/api/advert/" + advertId,
                method: "GET",
                async: async,
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    if(errorback == undefined) {
                        console.error("cannot load advert");
                    } else {
                        errorback();
                    }
                }
            });
        },
        saveAdvert: function (advert, callback, errorcallback) {
            $.ajax({
                url: "/api/advert",
                type: "POST",
                data: JSON.stringify(advert),
                dataType : 'json',
                contentType : "application/json",
                success: function (data) {
                    callback(data);
                },
                error: function (error) {
                    errorcallback(error);
                }
            });
        },
        loadFilteringPageWithOrderedAdverts: function (orderCriteria, searchCriteria, callback) {
            var criteria = {orderCriteria: orderCriteria, searchCriteria: searchCriteria};
            $.ajax({
                url: "/api/advert/page/search",
                type: "POST",
                data: JSON.stringify(criteria),
                dataType : 'json',
                contentType : "application/json",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load page with adverts");
                }
            });

        }

    };

    return methods;

}());
