import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-config-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
      FormsModule,
  ReactiveFormsModule
  ],
  templateUrl: './config-form.component.html',
  styleUrls: ['./config-form.component.css']
})
export class ConfigFormComponent {
  formGroup: FormGroup;
  generated = '';

  fields = [
    { name: 'firstName', label: 'Nom' },
    { name: 'lastName', label: 'Prénom' },
    { name: 'birthDate', label: 'Date de naissance' },
    { name: 'counter', label: 'Compteur' },

  ];
  configId = Math.floor(Math.random() * 100000); 

  rules = [
    { configId: this.configId ,orderIndex: 1, field: 'firstName', length: 3, prefix: null, suffix: '-' },
    {configId: this.configId ,orderIndex: 2, field: 'lastName', length: 4, prefix: null, suffix: '_' },
    { configId: this.configId,orderIndex: 3, field: 'birthYear', length: 4, prefix: 'N', suffix: null ,dateFormat: "yyyy"},
    { configId: this.configId,orderIndex: 4, field: 'counter', length: 5, prefix: 'C', suffix: null, initialValue: 7 }
  ];

  constructor(private fb: FormBuilder, private http: HttpClient) {
   this.formGroup = this.fb.group({
  firstName: ['', Validators.required],
  lastName: ['', Validators.required],
  birthDate: ['', Validators.required],
  counter: [null, Validators.required]
});
  }

 submit() {
    if (this.formGroup.invalid) {
      alert('Veuillez remplir tous les champs obligatoires.');
      return;
    }
    const configId = this.configId; // Change cette valeur si besoin
    const dto = {
      id: configId,
      firstName: this.formGroup.value.firstName,
      lastName: this.formGroup.value.lastName,
      birthDate: this.formGroup.value.birthDate,
      counter: this.formGroup.value.counter
    };

    this.http.post('http://localhost:8081/api/rules', this.rules).subscribe({
    next: () => {
      this.http.post<string>(
        `http://localhost:8080/api/generator/generate/${configId}`,
        dto,
        { responseType: 'text' as 'json' }
      ).subscribe({
        next: (generated: string) => {
          this.generated = generated;
          console.log("Nom généré :", generated);
        },
        error: (err) => {
          console.error("Erreur génération :", err);
          alert("Erreur lors de la génération.");
        }
      });
    },
    error: (err) => {
      console.error("Erreur enregistrement règles :", err);
      alert("Erreur lors de l'enregistrement des règles.");
    }
  });

  }
}
