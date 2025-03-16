# Barcode Java API

![Build Status](https://github.com/barnhill/barcode-java-api/workflows/Barcode%20API%20CI/badge.svg)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/barnhill/barcode-java-api/blob/master/LICENSE)

Barcode Java API is a lightweight and easy-to-use Java library for generating barcodes. It supports various barcode formats and provides simple APIs for creating barcodes programmatically.

## Features

- **Multiple Barcode Formats:** Generate barcodes in various formats including Code 128, EAN-13, UPC-A, and more.
- **Customizable:** Easily customize barcode size, color, text, and other properties to suit your needs.
- **Platform Independent:** Works seamlessly on any platform that supports Java and Docker.
- **Lightweight:** Small footprint, ideal for integration into existing projects without adding unnecessary overhead.
- **Open Source:** Licensed under the MIT License, allowing for free and open usage in both commercial and non-commercial projects.

## Supported Symbologies

|   Supported   |  Symbology    | List  |
| :------------- | :------------- | :-----|
| Code 128      | Code 93       | Code 39 (Extended / Full ASCII) |
| Code11        | EAN-8         | FIM (Facing Identification Mark) |
| UPC-A         | UPC-E         | Pharmacode   |
| MSI           | PostNet       | Standard 2 of 5 |
| ISBN          | Codabar       | Interleaved 2 of 5 |
| ITF-14        | Telepen       | UPC Supplemental 2 |
| JAN-13        | EAN-13        | UPC Supplemental 5 |

## Usage

To use SSL:

- Mount a docker volume `/ssl` that points to a host folder that contains the SSL certificates for the server.
Name the full chain certificate as `certificate.pem` and the private key as `key.pem`
- Pass the environment variable to the container: `PROFILE=ssl`

```
docker run --rm -p -e PROFILE=ssl 8443:8443 bradbarnhill/barcode-api
```

To use without SSL:
- Pass the environment variable to the container: `PROFILE=nossl` or pass no value for `PROFILE`

```
docker run --rm -p 8080:8080 bradbarnhill/barcode-api
```


### Test
To generate a sample barcode using this API go to:
#### SSL
```
https://localhost:8443/v1/barcode/upca/data/636711605328?imageFormat=png&w=600&h=300&label=false
```

#### No SSL
```
http://localhost:8080/v1/barcode/upca/data/636711605328?imageFormat=png&w=600&h=300&label=false
```

## Response Headers
#### Error codes will be returned in the headers for cases of invalid input parameters.

|      Header       |                                              Value                                              |
|:-----------------:|:-----------------------------------------------------------------------------------------------:|
| x-error |                                     EUPCA-1: Data length invalid. (Length must be 11 or 12)        |

#### Standard Headers

|      Header       |                                              Value                                              |
|:-----------------:|:-----------------------------------------------------------------------------------------------:|
| X-Barcode-Version |                                     Barcode for Java 2.7.3                                      |
|    X-Draw-Time    |                                           0.374072 ms                                           |
|  X-Encoded-Type   |                                              UPCA                                               |
|  X-Encoded-Value  | 10100110010010011011110101000110110001010111101010100010010010001110100111001011001101101100101 |
|  X-Encoding-Time  |                                           0.576274 ms                                           |
|   X-Label-Font    |                                              Serif                                              |
|    X-Raw-Value    |                                          123456789012                                           |
|    X-Served-By    |                                   barcode-api-c9f7bdd88-rq9wp                                   |
|    X-Served-By    |                                     barcode.someserver.com                                      |

## Documentation:
#### SSL
```
https://localhost:8443/swagger-ui/index.html
```

#### No SSL
```
http://localhost:8080/swagger-ui/index.html
```

## Contributing

Contributions are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request. Make sure to follow the [Contributing Guidelines](CONTRIBUTING.md).

## License

Barcode Java API is licensed under the [MIT License](LICENSE).