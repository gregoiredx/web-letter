<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Web Letter</title>
    <link href="${staticUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${staticUrl}/js/jquery-1.11.2.min.js"></script>
    <script src="${staticUrl}/bootstrap/js/bootstrap.min.js"></script>
    <style type="text/css">
        label {
            font-weight: normal !important;
        }
    </style>
</head>
<body>

    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Web Letter</a>
            </div>
        </div>
    </nav>

    <form name="letterForm" action="${pdfLetterUrl}" method="post" target="_blank" class="form-horizontal">
        <div class="form-group">
            <label for="sender_name" class="col-sm-2 control-label">Nom de l'expediteur:</label>
            <div class="col-sm-10">
                <input type="text" name="sender_name" value="${(letter.sender.name)!}" class="form-control" />
            </div>
        </div>
        <div class="form-group">
            <label for="sender_address" class="col-sm-2 control-label">Adresse de l'expediteur: </label>
            <div class="col-sm-10">
                <textarea rows="4" name="sender_address" class="form-control">${(letter.sender.address)!}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="receiver_name" class="col-sm-2 control-label">Nom du destinataire:</label>
            <div class="col-sm-10">
                <input type="text" name="receiver_name" value="${(letter.receiver.name)!}" class="form-control" />
            </div>
        </div>
        <div class="form-group">
            <label for="receiver_address" class="col-sm-2 control-label">Adresse du destinataire: </label>
            <div class="col-sm-10">
                <textarea rows="4" name="receiver_address" class="form-control">${(letter.receiver.address)!}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="salutation" class="col-sm-2 control-label">Salutation:</label>
            <div class="col-sm-10">
                <input type="text" name="salutation" value="${(letter.salutation)!}" class="form-control" />
            </div>
        </div>
        <div class="form-group">
            <label for="body" class="col-sm-2 control-label">Corps: </label>
            <div class="col-sm-10">
                <textarea rows="20" name="body" class="form-control">${(letter.body)!}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="closing" class="col-sm-2 control-label">Fermeture:</label>
            <div class="col-sm-10">
                <input type="text" name="closing" value="${(letter.closing)!}" class="form-control" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input name="submit" type="submit" class="btn btn-default" value="pdf" />
            </div>
        </div>
    </form>
</body>
</html>