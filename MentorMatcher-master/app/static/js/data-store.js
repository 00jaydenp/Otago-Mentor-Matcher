export const dataStore = Vuex.createStore({

    state() {
        selectedMentor: null;
        personUser: null;
        menteeUser: null;
        mentorUser: null;
        adminUser: null;
    },

    mutations: {
        selectMentor(state, mentor) {
            state.selectedMentor = mentor;
        },

        personUser(state, person) {
            state.personUser = person;
        },

        menteeUser(state, mentee) {
            state.menteeUser = mentee;
        },

        mentorUser(state, mentor) {
            state.mentorUser = mentor;
        },

        adminUser(state, person) {
            state.adminUser = person;
        }
    },

    // add session storage persistence
    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});


