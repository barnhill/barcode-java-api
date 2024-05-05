# barcode-java-api ![Barcode API CI](https://github.com/barnhill/barcode-java-api/workflows/Barcode%20API%20CI/badge.svg)
RESTful API for barcode image generation library

# Barcode Java API

![Build Status](https://github.com/barnhill/barcode-java-api/workflows/Barcode%20API%20CI/badge.svg)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/barnhill/barcode-java-api/blob/master/LICENSE)

Barcode Java API is a lightweight and easy-to-use Java library for generating barcodes. It supports various barcode formats and provides simple APIs for creating barcodes programmatically.

## Features

- **Multiple Barcode Formats:** Generate barcodes in various formats including Code 128, EAN-13, UPC-A, and more.
- **Customizable:** Easily customize barcode size, color, text, and other properties to suit your needs.
- **Platform Independent:** Works seamlessly on any platform that supports Java and Docker.
- **Lightweight:** Small footprint, ideal for integration into existing projects without adding unnecessary overhead.
- **Open Source:** Licensed under the Apache 2.0 License, allowing for free and open usage in both commercial and non-commercial projects.

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

```
docker run --rm -p 8080:8080 bradbarnhill/barcode-api
```

Then browse to the following url to check out the documentation:
```
http://localhost:8080/swagger-ui/index.html
```

To generate a sample barcode using this API go to:
```
http://localhost:8080/v1/barcode/upca/data/636711605328?imageFormat=png&w=600&h=300&label=false
```

Error codes will be returned in the headers for cases of invalid input parameters.

## Contributing

Contributions are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request. Make sure to follow the [Contributing Guidelines](CONTRIBUTING.md).

## License

Barcode Java API is licensed under the [MIT License](LICENSE).