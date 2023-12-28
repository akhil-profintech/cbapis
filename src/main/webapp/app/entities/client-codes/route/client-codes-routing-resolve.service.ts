import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IClientCodes } from '../client-codes.model';
import { ClientCodesService } from '../service/client-codes.service';

export const clientCodesResolve = (route: ActivatedRouteSnapshot): Observable<null | IClientCodes> => {
  const id = route.params['id'];
  if (id) {
    return inject(ClientCodesService)
      .find(id)
      .pipe(
        mergeMap((clientCodes: HttpResponse<IClientCodes>) => {
          if (clientCodes.body) {
            return of(clientCodes.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default clientCodesResolve;
