import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFinanceRequest, NewFinanceRequest } from '../finance-request.model';

export type PartialUpdateFinanceRequest = Partial<IFinanceRequest> & Pick<IFinanceRequest, 'id'>;

type RestOf<T extends IFinanceRequest | NewFinanceRequest> = Omit<T, 'requestDate' | 'dueDate'> & {
  requestDate?: string | null;
  dueDate?: string | null;
};

export type RestFinanceRequest = RestOf<IFinanceRequest>;

export type NewRestFinanceRequest = RestOf<NewFinanceRequest>;

export type PartialUpdateRestFinanceRequest = RestOf<PartialUpdateFinanceRequest>;

export type EntityResponseType = HttpResponse<IFinanceRequest>;
export type EntityArrayResponseType = HttpResponse<IFinanceRequest[]>;

@Injectable({ providedIn: 'root' })
export class FinanceRequestService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/finance-requests');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(financeRequest: NewFinanceRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(financeRequest);
    return this.http
      .post<RestFinanceRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(financeRequest: IFinanceRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(financeRequest);
    return this.http
      .put<RestFinanceRequest>(`${this.resourceUrl}/${this.getFinanceRequestIdentifier(financeRequest)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(financeRequest: PartialUpdateFinanceRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(financeRequest);
    return this.http
      .patch<RestFinanceRequest>(`${this.resourceUrl}/${this.getFinanceRequestIdentifier(financeRequest)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFinanceRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFinanceRequest[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFinanceRequestIdentifier(financeRequest: Pick<IFinanceRequest, 'id'>): number {
    return financeRequest.id;
  }

  compareFinanceRequest(o1: Pick<IFinanceRequest, 'id'> | null, o2: Pick<IFinanceRequest, 'id'> | null): boolean {
    return o1 && o2 ? this.getFinanceRequestIdentifier(o1) === this.getFinanceRequestIdentifier(o2) : o1 === o2;
  }

  addFinanceRequestToCollectionIfMissing<Type extends Pick<IFinanceRequest, 'id'>>(
    financeRequestCollection: Type[],
    ...financeRequestsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const financeRequests: Type[] = financeRequestsToCheck.filter(isPresent);
    if (financeRequests.length > 0) {
      const financeRequestCollectionIdentifiers = financeRequestCollection.map(
        financeRequestItem => this.getFinanceRequestIdentifier(financeRequestItem)!,
      );
      const financeRequestsToAdd = financeRequests.filter(financeRequestItem => {
        const financeRequestIdentifier = this.getFinanceRequestIdentifier(financeRequestItem);
        if (financeRequestCollectionIdentifiers.includes(financeRequestIdentifier)) {
          return false;
        }
        financeRequestCollectionIdentifiers.push(financeRequestIdentifier);
        return true;
      });
      return [...financeRequestsToAdd, ...financeRequestCollection];
    }
    return financeRequestCollection;
  }

  protected convertDateFromClient<T extends IFinanceRequest | NewFinanceRequest | PartialUpdateFinanceRequest>(
    financeRequest: T,
  ): RestOf<T> {
    return {
      ...financeRequest,
      requestDate: financeRequest.requestDate?.format(DATE_FORMAT) ?? null,
      dueDate: financeRequest.dueDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFinanceRequest: RestFinanceRequest): IFinanceRequest {
    return {
      ...restFinanceRequest,
      requestDate: restFinanceRequest.requestDate ? dayjs(restFinanceRequest.requestDate) : undefined,
      dueDate: restFinanceRequest.dueDate ? dayjs(restFinanceRequest.dueDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFinanceRequest>): HttpResponse<IFinanceRequest> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFinanceRequest[]>): HttpResponse<IFinanceRequest[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
