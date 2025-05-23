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
    { configId: this.configId,orderIndex: 3, field: 'birthDate', length: 4, prefix: 'N', suffix: null ,dateFormat: "yyyy"},
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

 isSubmitting = false;
rulesPosted = false;

submit() {
  if (this.formGroup.invalid) {
    alert('Veuillez remplir tous les champs obligatoires.');
    return;
  }

  if (this.isSubmitting) {
    return;
  }

  this.isSubmitting = true;

  const configId = this.configId;

  // Incrémente le compteur avant chaque génération
  const currentCounter = this.formGroup.value.counter || 0;
  this.formGroup.patchValue({ counter: currentCounter + 1 });

  const dto = {
    id: configId,
    firstName: this.formGroup.value.firstName,
    lastName: this.formGroup.value.lastName,
    birthDate: this.formGroup.value.birthDate,
    counter: this.formGroup.value.counter
  };

  const generate = () => {
    this.http.post<string>(
      `http://localhost:8080/api/generator/generate/${configId}`,
      dto,
      { responseType: 'text' as 'json' }
    ).subscribe({
      next: (generated: string) => {
        this.generated = generated;
        console.log("Nom généré :", generated);
        this.isSubmitting = false;
      },
      error: (err) => {
        console.error("Erreur génération :", err);
        alert("Erreur lors de la génération.");
        this.isSubmitting = false;
      }
    });
  };

  if (!this.rulesPosted) {
    // Poster les règles une seule fois
    this.http.post('http://localhost:8081/api/rules', this.rules).subscribe({
      next: () => {
        this.rulesPosted = true;
        generate();
      },
      error: (err) => {
        console.error("Erreur enregistrement règles :", err);
        alert("Erreur lors de l'enregistrement des règles.");
        this.isSubmitting = false;
      }
    });
  } else {
    generate();
  }
}
}
