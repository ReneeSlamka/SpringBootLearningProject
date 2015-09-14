$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/nameGenerator"
    }).then(function(data, status, jqxhr) {
       $('.user-name').append(data.name);
       $('.user-age').append(data.age);
       console.log(jqxhr);
    });
});