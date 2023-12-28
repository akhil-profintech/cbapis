import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IInstaAlert } from '../insta-alert.model';
import { InstaAlertService } from '../service/insta-alert.service';

export const instaAlertResolve = (route: ActivatedRouteSnapshot): Observable<null | IInstaAlert> => {
  const id = route.params['id'];
  if (id) {
    return inject(InstaAlertService)
      .find(id)
      .pipe(
        mergeMap((instaAlert: HttpResponse<IInstaAlert>) => {
          if (instaAlert.body) {
            return of(instaAlert.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default instaAlertResolve;
