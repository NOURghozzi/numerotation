import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ConfigFormComponent } from "./config-form/config-form.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ConfigFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'generator-app';
}
