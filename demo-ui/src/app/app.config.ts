import {APP_INITIALIZER, ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import {provideToastr} from "ngx-toastr";
import {KeycloakService} from "./services/keycloak/keycloak.service";
import {httpTokenInterceptor} from "./services/interceptor/http-token.service";

export function kcFactory(kcService: KeycloakService){
  return () => kcService.init()
}

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({
    eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([
        httpTokenInterceptor
      ]),
    ),
    provideToastr(),
    {
      provide: APP_INITIALIZER,
      useFactory: kcFactory,
      deps: [KeycloakService],
      multi: true
    }
  ]
};
