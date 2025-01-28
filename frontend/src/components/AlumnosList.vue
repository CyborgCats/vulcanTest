<template>
    <div>
      <h1>Lista de Alumnos</h1>
      <ul v-if="alumnos.length">
        <li v-for="alumno in alumnos" :key="alumno.id">
          {{ alumno.nombre }} - {{ alumno.edad }} años - {{ alumno.genero }}
        </li>
      </ul>
      <p v-else>Cargando alumnos...</p>
    </div>
  </template>
  
  <script>
  import api from '../services/api';
  
  export default {
    data() {
      return {
        alumnos: [],
      };
    },
    async created() {
      try {
        const response = await api.get('/alumnos');
        this.alumnos = response.data;
      } catch (error) {
        console.error('Error al cargar alumnos:', error);
      }
    },
  };
  </script>
  