import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRequestOffer, NewRequestOffer } from '../request-offer.model';

export type PartialUpdateRequestOffer = Partial<IRequestOffer> & Pick<IRequestOffer, 'id'>;

type RestOf<T extends IRequestOffer | NewRequestOffer> = Omit<T, 'financeRequestDate'> & {
  financeRequestDate?: string | null;
};

export type RestRequestOffer = RestOf<IRequestOffer>;

export type NewRestRequestOffer = RestOf<NewRequestOffer>;

export type PartialUpdateRestRequestOffer = RestOf<PartialUpdateRequestOffer>;

export type EntityResponseType = HttpResponse<IRequestOffer>;
export type EntityArrayResponseType = HttpResponse<IRequestOffer[]>;

@Injectable({ providedIn: 'root' })
export class RequestOfferService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/request-offers');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(requestOffer: NewRequestOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requestOffer);
    return this.http
      .post<RestRequestOffer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(requestOffer: IRequestOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requestOffer);
    return this.http
      .put<RestRequestOffer>(`${this.resourceUrl}/${this.getRequestOfferIdentifier(requestOffer)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(requestOffer: PartialUpdateRequestOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requestOffer);
    return this.http
      .patch<RestRequestOffer>(`${this.resourceUrl}/${this.getRequestOfferIdentifier(requestOffer)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestRequestOffer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestRequestOffer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRequestOfferIdentifier(requestOffer: Pick<IRequestOffer, 'id'>): number {
    return requestOffer.id;
  }

  compareRequestOffer(o1: Pick<IRequestOffer, 'id'> | null, o2: Pick<IRequestOffer, 'id'> | null): boolean {
    return o1 && o2 ? this.getRequestOfferIdentifier(o1) === this.getRequestOfferIdentifier(o2) : o1 === o2;
  }

  addRequestOfferToCollectionIfMissing<Type extends Pick<IRequestOffer, 'id'>>(
    requestOfferCollection: Type[],
    ...requestOffersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const requestOffers: Type[] = requestOffersToCheck.filter(isPresent);
    if (requestOffers.length > 0) {
      const requestOfferCollectionIdentifiers = requestOfferCollection.map(
        requestOfferItem => this.getRequestOfferIdentifier(requestOfferItem)!,
      );
      const requestOffersToAdd = requestOffers.filter(requestOfferItem => {
        const requestOfferIdentifier = this.getRequestOfferIdentifier(requestOfferItem);
        if (requestOfferCollectionIdentifiers.includes(requestOfferIdentifier)) {
          return false;
        }
        requestOfferCollectionIdentifiers.push(requestOfferIdentifier);
        return true;
      });
      return [...requestOffersToAdd, ...requestOfferCollection];
    }
    return requestOfferCollection;
  }

  protected convertDateFromClient<T extends IRequestOffer | NewRequestOffer | PartialUpdateRequestOffer>(requestOffer: T): RestOf<T> {
    return {
      ...requestOffer,
      financeRequestDate: requestOffer.financeRequestDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restRequestOffer: RestRequestOffer): IRequestOffer {
    return {
      ...restRequestOffer,
      financeRequestDate: restRequestOffer.financeRequestDate ? dayjs(restRequestOffer.financeRequestDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestRequestOffer>): HttpResponse<IRequestOffer> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestRequestOffer[]>): HttpResponse<IRequestOffer[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
