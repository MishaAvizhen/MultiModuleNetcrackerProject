/**
 * Created by Александр on 23.11.2016.
 */
$(function () {

    var cartCtrl = (function () {
        var methods = {
            init: function () {
                this.showCart();
                this.bindEvents();
            },
            showCart: function () {
                var adverts = JSON.parse($("#advertsInput").val());
                for (var i = 0; i < adverts.length; i++) {
                    AdvertService.loadAdvert(adverts[i], false, function (advert) {
                            var appendPanel = createPanel(advert, adverts[i]);
                            $("#contentContainer").append(appendPanel);
                        });
                }
            },
            bindEvents: function () {
                $('#contentContainer').delegate('button.removeAdvertFromCart', 'click', function () {
                    var advertId = this.getAttribute("advert");
                    CartService.removeAdvertFromCart(advertId, function (data) {
                        if (+data == -1) {
                            $("#removeAdvertFromCartMsg" + advertId).empty();
                            $("#removeAdvertFromCartMsg" + advertId).append($.i18n.prop('advert.notFound'));
                            return;
                        }
                        $("#cartSize").empty();
                        $("#cartSize").append(data);
                        $("#removeAdvertFromCartMsg" + advertId).empty();
                        $("#removeAdvertFromCartMsg" + advertId).append($.i18n.prop('advert.wasRemoved'));
                        $("#panel" + advertId).remove();
                    });
                });
            }

        };


        function createPanel(advert, advertId) {
            if (advert == null) {
                var notFoundPanel = $('<div>', {class: 'panel panel-default', id: 'panel' + advertId});
                var notFoundContent = $('<div>', {class: 'row'});
                var notFoundDiv = $('<div>', {class: 'col-md-5 col-lg-6'});
                notFoundDiv.append("<h3>" + $.i18n.prop('advert.notFound') + "</h3>");
                notFoundContent.append(notFoundDiv);
                notFoundPanel.append(notFoundContent);
                var removeAdvertFromCartDiv = createRemoveAdvertFromCartDiv(advertId);
                notFoundPanel.append(removeAdvertFromCartDiv);
                return notFoundPanel;
            }
            var $panel = $('<div>', {class: 'panel panel-default', id: 'panel' + advertId});
            var $body = $('<div>', {class: 'panel-body'});
            var $content = $('<div>', {class: 'row'});
            var $link = $('<a>', {href: "/advert/" + advert.id});
            var car = advert.car;
            var more = $.i18n.prop('msg.more');
            $link.append(more);

            var contentDiv = $('<div>', {class: 'col-md-5 col-lg-6'});
            contentDiv.append(car.make + " " + car.model + "<br>");
            contentDiv.append(advert.description + "<br>");
            contentDiv.append($.i18n.prop('car.year') + ": " + car.year + "<br>");
            var br = $.i18n.prop('currency.br');
            contentDiv.append(car.price + " " + br + "<br>");
            $content.append(contentDiv);

            var linkDiv = $('<div>', {class: 'col-md-2 col-lg-2'});
            linkDiv.append($link);
            $content.append(linkDiv);

            removeAdvertFromCartDiv = createRemoveAdvertFromCartDiv(advertId);
            $content.append(removeAdvertFromCartDiv);

            $body.append($content);
            $panel.append($body);
            return $panel;
        }

        function createRemoveAdvertFromCartDiv(advertId) {
            var panel = $('<div>');
            var removeBtn = $('<button>', {class: 'btn btn-primary removeAdvertFromCart', advert: advertId});
            removeBtn.text($.i18n.prop('advert.remove'));
            panel.append(removeBtn);
            var msgDiv = $('<div>', {id: 'removeAdvertFromCartMsg' + advertId});
            panel.append(msgDiv);
            return panel;
        }

        return methods;
    }());

    cartCtrl.init();

});