import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDisbursement, NewDisbursement } from '../disbursement.model';

export type PartialUpdateDisbursement = Partial<IDisbursement> & Pick<IDisbursement, 'id'>;

type RestOf<T extends IDisbursement | NewDisbursement> = Omit<T, 'offerAcceptedDate' | 'dbmtRequestDate'> & {
  offerAcceptedDate?: string | null;
  dbmtRequestDate?: string | null;
};

export type RestDisbursement = RestOf<IDisbursement>;

export type NewRestDisbursement = RestOf<NewDisbursement>;

export type PartialUpdateRestDisbursement = RestOf<PartialUpdateDisbursement>;

export type EntityResponseType = HttpResponse<IDisbursement>;
export type EntityArrayResponseType = HttpResponse<IDisbursement[]>;

@Injectable({ providedIn: 'root' })
export class DisbursementService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/disbursements');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(disbursement: NewDisbursement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(disbursement);
    return this.http
      .post<RestDisbursement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(disbursement: IDisbursement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(disbursement);
    return this.http
      .put<RestDisbursement>(`${this.resourceUrl}/${this.getDisbursementIdentifier(disbursement)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(disbursement: PartialUpdateDisbursement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(disbursement);
    return this.http
      .patch<RestDisbursement>(`${this.resourceUrl}/${this.getDisbursementIdentifier(disbursement)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestDisbursement>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDisbursement[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDisbursementIdentifier(disbursement: Pick<IDisbursement, 'id'>): number {
    return disbursement.id;
  }

  compareDisbursement(o1: Pick<IDisbursement, 'id'> | null, o2: Pick<IDisbursement, 'id'> | null): boolean {
    return o1 && o2 ? this.getDisbursementIdentifier(o1) === this.getDisbursementIdentifier(o2) : o1 === o2;
  }

  addDisbursementToCollectionIfMissing<Type extends Pick<IDisbursement, 'id'>>(
    disbursementCollection: Type[],
    ...disbursementsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const disbursements: Type[] = disbursementsToCheck.filter(isPresent);
    if (disbursements.length > 0) {
      const disbursementCollectionIdentifiers = disbursementCollection.map(
        disbursementItem => this.getDisbursementIdentifier(disbursementItem)!,
      );
      const disbursementsToAdd = disbursements.filter(disbursementItem => {
        const disbursementIdentifier = this.getDisbursementIdentifier(disbursementItem);
        if (disbursementCollectionIdentifiers.includes(disbursementIdentifier)) {
          return false;
        }
        disbursementCollectionIdentifiers.push(disbursementIdentifier);
        return true;
      });
      return [...disbursementsToAdd, ...disbursementCollection];
    }
    return disbursementCollection;
  }

  protected convertDateFromClient<T extends IDisbursement | NewDisbursement | PartialUpdateDisbursement>(disbursement: T): RestOf<T> {
    return {
      ...disbursement,
      offerAcceptedDate: disbursement.offerAcceptedDate?.format(DATE_FORMAT) ?? null,
      dbmtRequestDate: disbursement.dbmtRequestDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restDisbursement: RestDisbursement): IDisbursement {
    return {
      ...restDisbursement,
      offerAcceptedDate: restDisbursement.offerAcceptedDate ? dayjs(restDisbursement.offerAcceptedDate) : undefined,
      dbmtRequestDate: restDisbursement.dbmtRequestDate ? dayjs(restDisbursement.dbmtRequestDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDisbursement>): HttpResponse<IDisbursement> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDisbursement[]>): HttpResponse<IDisbursement[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
