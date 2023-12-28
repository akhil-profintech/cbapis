import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICREHighlights } from '../cre-highlights.model';
import { CREHighlightsService } from '../service/cre-highlights.service';

export const cREHighlightsResolve = (route: ActivatedRouteSnapshot): Observable<null | ICREHighlights> => {
  const id = route.params['id'];
  if (id) {
    return inject(CREHighlightsService)
      .find(id)
      .pipe(
        mergeMap((cREHighlights: HttpResponse<ICREHighlights>) => {
          if (cREHighlights.body) {
            return of(cREHighlights.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default cREHighlightsResolve;
