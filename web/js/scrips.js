$('.popup-youtube, .popup-text').magnificPopup({
    disableOn: 320,
    type: 'iframe',
    mainClass: 'mfp-fade',
    removalDelay: 160,
    preloader: false,
    fixedContentPos: true
});
$('.sub-menu ul').hide();
$(".sub-menu a").click(function () {
    $(this).parent(".sub-menu").children("ul").slideToggle("100");
    $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
});


$('.btn-edit-note').click(function () {
    var id = $(this).data('id');
    var card = $(this).closest('.card');
    var title = card.find('.card-title').text();
    var cardText = card.find('.card-text')
    var text = cardText.text();
    cardText.html('<textarea class="form-control text-val" rows="5">' + text + '</textarea>');
    $(this).text('Save');
    $(this).unbind('click');

    $(this).click(function () {
        let text = card.find('.text-val').val();
        cardText.html(text);
        $(this).text('Edit');
        $(this).unbind('click');
        $.ajax({
            url: 'UpdateNoteController',
            type: 'POST',
            data: {
                title: title,
                id: id,
                text: text
            },
            success: function (data) {
                Swal.fire({
                    icon: 'success',
                    title: 'Your work has been saved',
                    conFirmButtonText: 'OK'
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.reload();
                    }
                });
            },
            error: function (data) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Something went wrong!',
                    conFirmButtonText: 'OK'
                });
            }
        });
    });
});