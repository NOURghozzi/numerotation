import { mergeApplicationConfig, ApplicationConfig } from '@angular/core';
import { provideServerRendering } from '@angular/platform-server';
import { provideServerRouting } from '@angular/ssr';
import { appConfig } from './app.config';
import { serverRoutes } from './app.routes.server';
import { FormGroup } from '@angular/forms';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { Component } from '@angular/core';
const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering(),
    provideServerRouting(serverRoutes),
    {
      provide: FormGroup,
      useValue: new FormGroup({})
    },
    {
      provide: HttpClient,
      useFactory: (handler: HttpHandler) => new HttpClient(handler),
      deps: [HttpHandler]
    }

  ]
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
