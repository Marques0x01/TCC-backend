<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
            color: black !important;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="800" style="border-collapse: collapse; border: solid 1px black">
    <tr>
        <td align="center" bgcolor="#4D4DE9" style="padding: 40px 0 30px 0;">
            <span style="font-size: 24px; color: white; font-weight: bold;">RENT SHOP</span>
        </td>
    </tr>
    <tr>
        <td bgcolor="white" style="padding: 20px 30px 20px 30px; font-size: 18px; color: black;">
            <p>Olá ${name},</p>
            <p>Percebos que precisa de uma confirmação para acesso ao nosso site, então vamos lá!</p>
            <p>Logo abaixo você receberá um código para que possa continuar com seu acesso com segurança.</p>
            <p>Basta copiar esse código e cola-lo no campo que foi pedido e pronto!</p>
            <p style="border: 1px solid black; padding: 8px; font-weight: bold; font-size: 19px">${token}</p>
            <p>Não deixe de entrar em contato com nossa equipe de suporte caso acha algum problema</p>
            <p>suporte@rentshop.com</p>
            <p>Obrigado pela preferência!</p>
        </td>
    </tr>
    <tr>
        <td bgcolor="#edeff1" style="padding: 15px 30px 15px 30px; font-size: 18px; color: black;">
            <p>${sender}</p>
            <p style="text-decoration: none">${signature}</p>
            <p>${location}</p>
        </td>
    </tr>
</table>

</body>
</html>