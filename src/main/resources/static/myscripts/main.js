$('document').ready(function () {
    $.ajax('/users/userAuth', {
        method: 'GET',
        success: function (user) {
            $('#nameTitle').text(user.name);
            user.roles.forEach(function (role) {
                $('#roleTitle').append('<strong>' + role.simpleName + ' </strong>');
            })
        }
    })
    showUsers();
})
//строит таблицу всех юзеров
function showUsers() {
    $('#users').empty();
    $.ajax("/users", {
        dataType: "json",
        method: 'GET',
        success: function (data) {
            let users = JSON.parse(JSON.stringify(data));
            users.forEach(function (user) {
                $("#users").append('<tr style="height: 63px;" id="tr' + user.id + '">' +
                    '<td style="width: 77px;height: 63px;" id="userId' + user.id + '">' + user.id + '</td>' +
                    '<td style="height: 63px;width: 200px;" id="userName' + user.id + '">' + user.name + '</td>' +
                    '<td style="height: 63px;width: 200px;" id="userRoles' + user.id + '"></td>' +
                    '<td style="height: 63px;width: 163px;">' +
                    '<button class="btn btn-info" type="button" data-toggle="modal" data-target="#edit" onclick="openEditeModal(' + user.id + ')">Edit</button>' +
                    '</td>' +
                    '<td style="height: 63px;width: 143px;">' +
                    '<button class="btn btn-danger" type="button" data-toggle="modal" data-target="#delete" onclick="openDeleteModal(' + user.id + ')">Delete</button>' +
                    '</td>' +
                    '</tr>');
                user.roles.forEach(function (role) {
                    $("#userRoles" + user.id).append('<span>' + role.simpleName + ' </span>');
                })
            })
        }
    })
}

//открывает модалку для удаления
function openDeleteModal(id) {
    let name = $('#userName' + id).text();
    let roles = $('#userRoles' + id).text().trim().split(" ");
    $('#delete #id').val(id);
    $('#delete #name').val(name);
    $('#delete #roles').empty();
    $.each(roles, function (key, value) {
        $('#delete #roles').append('<option value="' + key + '">' + value + '</option>');
    });
}

//кнопка в модалке удаляет юзверя
$('#deleteUser').on('click', function deleteUser() {
    let id = $('#delete #id').val();
    $.ajax('/users/' + id, {
        method: 'DELETE',
        success: function () {
            $("#users").find('#tr' + id).remove();
        }
    })
})

//открывает модалку редактирования
function openEditeModal(id) {
    let name = $('#userName' + id).text();
    $('#idEdite').val(id);
    $('#nameEdite').val(name);
}

//кнопка в модалке редактирования
$('.btn-primary').on('click', function (event) {
    event.preventDefault();
    let user = {
        id: $('#idEdite').val(),
        name: $('#nameEdite').val(),
        password: $('#passEdite').val(),
        roles: $('#rolesEdite').val()
    };
    $.ajax('/users/edit', {
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: 'application/JSON; charset=utf-8',
        method: 'PUT',
        success: function () {
            showUsers();
        }
    })
})

//добавление юзера
$('.btn-success').on('click', function (event) {
    event.preventDefault();
    let user = {
        name: $('#addName').val(),
        password: $('#addPass').val(),
        roles: $('#addRole').val()
    };
    $.ajax('/users/add', {
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: 'application/JSON; charset=utf-8',
        method: 'POST',
        success: function () {
            $('#tab1').addClass('active');
            $('#tab-1').addClass('active');
            $('#tab2').removeClass('active');
            $('#tab-2').removeClass('active');
            $('#addName').val('User name');
            $('#addPass').val('Password');
            showUsers();
        }
    })
})