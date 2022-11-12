"use strict";

// import data store
import { dataStore } from './data-store.js'

export const Footer = {

    template:
            `
    <footer>
        <div class='footer whiteText'>
            <div class='footerBox1'>
                 <img class='otagoUniLogo' src='otago_university_logo.jpeg' href='https://www.otago.ac.nz/'>
            </div>
                
            <div class='footerBox2'>
                <!-- Alba's contact details & social links -->
                <p><strong>Contact:</strong><br>Alba Suarez Garcia<br>Programme Co-ordinator<br>Tel +64 3 479 8244<br>Email mentor@otago.ac.nz</p>
            </div>
                
            <div class='footerBox3'>
                <!-- Career Development Center contact info -->
                <p>© Career Development Centre<br>PO Box 56<br>Dunedin 9054<br>New Zealand<br>Tel 64 3 479 8244<br>Fax 64 3 479 9148<br>Email careers@otago.ac.nz</p>
            </div>
        </div>       
    </footer>
    `
}; 