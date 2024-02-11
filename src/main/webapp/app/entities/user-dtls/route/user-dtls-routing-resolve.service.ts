import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUserDtls } from '../user-dtls.model';
import { UserDtlsService } from '../service/user-dtls.service';

export const userDtlsResolve = (route: ActivatedRouteSnapshot): Observable<null | IUserDtls> => {
  const id = route.params['id'];
  if (id) {
    return inject(UserDtlsService)
      .find(id)
      .pipe(
        mergeMap((userDtls: HttpResponse<IUserDtls>) => {
          if (userDtls.body) {
            return of(userDtls.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default userDtlsResolve;
