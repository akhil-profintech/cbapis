import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBeneValidation } from '../bene-validation.model';
import { BeneValidationService } from '../service/bene-validation.service';

export const beneValidationResolve = (route: ActivatedRouteSnapshot): Observable<null | IBeneValidation> => {
  const id = route.params['id'];
  if (id) {
    return inject(BeneValidationService)
      .find(id)
      .pipe(
        mergeMap((beneValidation: HttpResponse<IBeneValidation>) => {
          if (beneValidation.body) {
            return of(beneValidation.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default beneValidationResolve;
