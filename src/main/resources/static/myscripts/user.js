$('document').ready(function () {
    let roles = $('#userRoles').text().trim().split(" ");
    if (roles[0] === 'ADMIN' || roles[1] === 'ADMIN') {
        $('#nav').prepend('<li class="nav-item"><a class="nav-link border rounded" href="/admin">Admin</a></li>');
    }
})