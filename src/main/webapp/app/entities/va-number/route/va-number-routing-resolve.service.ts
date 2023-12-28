import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVANumber } from '../va-number.model';
import { VANumberService } from '../service/va-number.service';

export const vANumberResolve = (route: ActivatedRouteSnapshot): Observable<null | IVANumber> => {
  const id = route.params['id'];
  if (id) {
    return inject(VANumberService)
      .find(id)
      .pipe(
        mergeMap((vANumber: HttpResponse<IVANumber>) => {
          if (vANumber.body) {
            return of(vANumber.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default vANumberResolve;
