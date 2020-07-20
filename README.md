# Biometric-Authentication-using-PHE

This is a implementation of biometric authentication using partially homomorphic encryption. The encryption used is Goldwasser-Micali encryption system. The code is written in java.

## Databases
We created two databases in MYSQL. One is client database which contains plain fingerprints. And another is server database which contains Names and encrypted fingerprints.\
Attributes of databases\
clientdb - sl(integer), fingerprints(longtext)\
serverdb - name(varchar(50)), enfp(longtext)

## Cryptosystem
Goldwasser-Micali encryption and decryption\
We generated keys using Gen.java and hardcoded into encryption and decryption.
###### Encryption
Input - message (as string)\
Output - encrypted message (as biginteger matrix)
###### Decryption
Input - encrypted message (as biginteger matrix)\
Output - message (as string)

## Client slide 
Input - plain fingerprint \
Working - From client database the closest fingerfrint is selected using the input fingerprint. And it is encrypted and send to server.

## Server side
Input - encrypted fingerprint\
Working - encrypted input fingerprint is multiplied with each encrypted fingerprints in server database and decrypted. If the decrypted value is zero then the fingerprints matches and returns Name. If nothing matches returns "Name Not Found".
