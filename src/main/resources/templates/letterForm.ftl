<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Letter</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${staticUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${staticUrl}/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<script src="${staticUrl}/js/jquery-1.11.2.min.js"></script>
<script src="${staticUrl}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand" href="">Web Letter</a>
        </div>
    </div>

    <form name="letterForm" action="${pdfLetterUrl}" method="post" target="_blank" class="form-horizontal">
        <div class="control-group">
            <label for="sender_name" class="control-label">Nom de l'expediteur:</label>
            <div class="controls">
                <input type="text" name="sender_name" value="${(letter.sender.name)!}" class="input-block-level" />
            </div>
        </div>
        <div class="control-group">
            <label for="sender_address" class="control-label">Adresse de l'expediteur: </label>
            <div class="controls">
                <textarea rows="4" name="sender_address" class="input-block-level">${(letter.sender.address)!}</textarea>
            </div>
        </div>
        <div class="control-group">
            <label for="receiver_name" class="control-label">Nom du destinataire:</label>
            <div class="controls">
                <input type="text" name="receiver_name" value="${(letter.receiver.name)!}" class="input-block-level" />
            </div>
        </div>
        <div class="control-group">
            <label for="receiver_address" class="control-label">Adresse du destinataire: </label>
            <div class="controls">
                <textarea rows="4" name="receiver_address" class="input-block-level">${(letter.receiver.address)!}</textarea>
            </div>
        </div>
        <div class="control-group">
            <label for="salutation" class="control-label">Salutation:</label>
            <div class="controls">
                <input type="text" name="salutation" value="${(letter.salutation)!}" class="input-block-level" />
            </div>
        </div>
        <div class="control-group">
            <label for="body" class="control-label">Corps: </label>
            <div class="controls">
                <textarea rows="20" name="body" class="input-block-level">${(letter.body)!}</textarea>
            </div>
        </div>
        <div class="control-group">
            <label for="closing" class="control-label">Fermeture:</label>
            <div class="controls">
                <input type="text" name="closing" value="${(letter.closing)!}" class="input-block-level" />
            </div>
        </div>
        <div class="control-group">
            <div class="form-actions">
                <div class="btn-group">
                    <button name="submit" type="submit" class="btn">pdf</button>
                </div>
            </div>
        </div>
    </form>
</body>
</html>