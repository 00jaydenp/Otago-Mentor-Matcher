/* global axios, Vue, Vuex, vm */

var logInApi = ({email}, {password}) => `/api/login/${email}/${password}`;
var mentorApi = ({person_ID}) => `/api/person/mentor/${person_ID}`;
var menteeApi = ({person_ID}) => `/api/person/mentee/${person_ID}`;
const app = Vue.createApp({

    data() {
        return {
            signInUser: new Object,
            user: new Object({
                person: new Object()
            }),
            person: new Object()


        };
    },

    computed: Vuex.mapState({
        selectedMentor: 'selectedMentor',
        menteeUser: 'menteeUser',
        mentorUser: 'mentorUser',
        adminUser: 'adminUser'

    }),

    mounted() {

    },

    methods: {
        /*
         * Allows an existing user to sign in to the system with their email and password. 
         * Commits the corresponding mentee/mentor information to that user upon login
         * @param user the user signing in.
         */
        signIn(user) {
            axios.get(logInApi({'email': user.email}, {'password': user.password}))
                    .then(response => {
                        if (response.data.active) {
                            this.person = response.data;
                            if (this.person.role === "Mentor" || this.person.role === "Admin") {
                                axios.get(mentorApi({'person_ID': this.person.personID}))
                                        .then(response => {
                                            this.user = response.data;
                                            this.user.person = this.person;
                                            dataStore.commit("mentorUser", this.user);
                                            if (this.person.role === "Admin") {
                                                dataStore.commit("adminUser", this.user);
                                            }
                                            window.location = 'home.html';
                                        })
                                        .catch(error => {
                                            console.error(error);
                                            alert("An error occurred - check the console for details.");
                                        });
                            } else if (this.person.role === "Mentee") {
                                axios.get(menteeApi({'person_ID': this.person.personID}))
                                        .then(response => {
                                            this.user = response.data;
                                            this.user.person = this.person;
                                            dataStore.commit("menteeUser", this.user);
                                            window.location = 'home.html';
                                        })
                                        .catch(error => {
                                            console.error(error);
                                            alert("An error occurred - check the console for details.");
                                        });
                            }
                        } else {
                            alert("No Account with that Email and Password")
                        }
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });
        }
    },

    mixins: []

});


// import data store
import { dataStore } from './data-store.js'
        app.use(dataStore);

app.mount("main");