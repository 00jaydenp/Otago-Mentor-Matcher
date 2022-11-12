/* global Vue, axios, Vuex */

"use strict";
var registerPersonAPI = '/api/user';
var photo;

const app = Vue.createApp({
    data() {
        return{
            file: '',
            person: new Object({
                profilePhoto: null,
                interests: new Array(),
                skills: new Array()
            })
        };
    },

    computed: Vuex.mapState({
        personUser: 'personUser'
    }),

    mounted() {

    },

    methods: {
        /*
         * Registers the basic details of a user (person) in the system, before relocating to desired user type registration page.
         * @param role the role of the person registering (Mentee or Mentor)
         */
        registerPerson(role) {
            if (this.person.password === document.getElementById("checkPassword").value) {
                axios.get(registerPersonAPI)
                        .then(response => {
                            var id = response.data.length + 10;
                            this.person.personID = id;
                            this.person.role = role;
                            this.person.active = true;
                            this.person.profilePhoto = photo;
                            dataStore.commit("personUser", this.person);
                            axios.post(registerPersonAPI, this.person)
                                    .then(() => {
                                        if (role === "Mentor") {
                                            window.location = 'registermentor.html';
                                        } else if (role === "Mentee") {
                                            window.location = 'registermentee.html';
                                        }
                                    });
                        });
            } else {
                alert("ERROR");
            }
        },

        /*
         * Allows a user to select an image they wish to upload as a profile photo as part of registration.
         */
        selectFile() {
            var img = this.$refs.file.files[0];
            var reader = new FileReader();
            reader.onloadend = function () {
                photo = reader.result;
                var image = document.getElementById("profile");
                image.src = reader.result;
                image.style.width = "200px";
                image.style.height = "200px";
            };
            reader.readAsDataURL(img);
        }
    }
});


// import data store
import { dataStore } from './data-store.js'
        app.use(dataStore);

app.mount("main");
