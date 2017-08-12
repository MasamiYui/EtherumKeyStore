var keythereum = require(keythereum);
createKeyObject = function(password, options) {
    var params = { keyBytes 32, ivBytes 16 };
    var dk = keythereum.create(params);
     if options is unspecified, the values in keythereum.constants are used.
    var options = options  {
        kdf pbkdf2,
        cipher aes-128-ctr,
        kdfparams {
            c 262144,
            dklen 32,
            prf hmac-sha256
        }
    };
    var keyObject = keythereum.dump(password, dk.privateKey, dk.salt, dk.iv, options);
    console.log(keyObject.address);
    return keyObject;
};