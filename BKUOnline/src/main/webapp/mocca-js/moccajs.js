
var mocca_js = {};

var WorkflowExe;
define('moccajs', function(require) {

    var algorithmId = 'rsa-sha256';
    mocca_js.backend = require('backend');
    mocca_js.stal = require('stal');
    mocca_js.errorHandler = require('errorHandler');
    var _log = log.getInstance('moccajs.js');
    _log.debug('mocca_js: ' + JSON.stringify(mocca_js));
    _log.debug('mocca_js initialized!');
    var _parameters;

    function mockSTAL() {
        mocca_js.stal = require('stalMock');
    }

    function run(parameters, isMockSTAL) {
        if (isMockSTAL && isMockSTAL === true) {
            mockSTAL();
        }
        _log.debug('starting mocca-js with parameters: ' + JSON.stringify(parameters));
        _parameters = parameters;
        mocca_js.backend.connect()
            .then(selectCertificate)
            .then(sendCertificate)
            .then(parseDataToBeSigned)
            .then(sendSignedData)
            .then(parseSignedDataResponse)
            .then(redirectUser)
            .fail(mocca_js.errorHandler.handleError);
    }

    function selectCertificate() {
        var deferred = $.Deferred();
        var certificate = mocca_js.stal.selectCertificate();
        deferred.resolve(certificate);
        return deferred.promise();
    }

    function sendCertificate(certificate) {
        var deferred = $.Deferred();
        _log.debug('selectedCertificate: ' + certificate);
        mocca_js.backend.sendCertificate(_parameters.SessionID, certificate).then(function (data, textStatus, jqXHR) {
            deferred.resolve(data, certificate);
        });
        return deferred.promise();
    }

    function parseDataToBeSigned(responseData, certificate) {
        var deferred = $.Deferred();
        _log.debug('received certificate response: ' + responseData);
        var dataToBeSigned = $(responseData).find('SignedInfo').text();
        var signedData = mocca_js.stal.sign(certificate, algorithmId, dataToBeSigned);
        deferred.resolve(signedData);
        return deferred.promise();
    }

    function sendSignedData(signedData) {
        _log.debug("Signed data: " + signedData);
        return mocca_js.backend.sendSignedData(_parameters.SessionID, signedData);
    }

    function parseSignedDataResponse(response) {
        _log.debug("received signed data response: " + response);
    }

    function redirectUser() {
        parent.document.location.href = _parameters.RedirectURL;
    }

    return {
        run: run
    }
});