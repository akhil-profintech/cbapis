import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAcceptedOffer, NewAcceptedOffer } from '../accepted-offer.model';

export type PartialUpdateAcceptedOffer = Partial<IAcceptedOffer> & Pick<IAcceptedOffer, 'id'>;

type RestOf<T extends IAcceptedOffer | NewAcceptedOffer> = Omit<T, 'offerDate' | 'offerAcceptedDate'> & {
  offerDate?: string | null;
  offerAcceptedDate?: string | null;
};

export type RestAcceptedOffer = RestOf<IAcceptedOffer>;

export type NewRestAcceptedOffer = RestOf<NewAcceptedOffer>;

export type PartialUpdateRestAcceptedOffer = RestOf<PartialUpdateAcceptedOffer>;

export type EntityResponseType = HttpResponse<IAcceptedOffer>;
export type EntityArrayResponseType = HttpResponse<IAcceptedOffer[]>;

@Injectable({ providedIn: 'root' })
export class AcceptedOfferService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/accepted-offers');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(acceptedOffer: NewAcceptedOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(acceptedOffer);
    return this.http
      .post<RestAcceptedOffer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(acceptedOffer: IAcceptedOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(acceptedOffer);
    return this.http
      .put<RestAcceptedOffer>(`${this.resourceUrl}/${this.getAcceptedOfferIdentifier(acceptedOffer)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(acceptedOffer: PartialUpdateAcceptedOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(acceptedOffer);
    return this.http
      .patch<RestAcceptedOffer>(`${this.resourceUrl}/${this.getAcceptedOfferIdentifier(acceptedOffer)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestAcceptedOffer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAcceptedOffer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAcceptedOfferIdentifier(acceptedOffer: Pick<IAcceptedOffer, 'id'>): number {
    return acceptedOffer.id;
  }

  compareAcceptedOffer(o1: Pick<IAcceptedOffer, 'id'> | null, o2: Pick<IAcceptedOffer, 'id'> | null): boolean {
    return o1 && o2 ? this.getAcceptedOfferIdentifier(o1) === this.getAcceptedOfferIdentifier(o2) : o1 === o2;
  }

  addAcceptedOfferToCollectionIfMissing<Type extends Pick<IAcceptedOffer, 'id'>>(
    acceptedOfferCollection: Type[],
    ...acceptedOffersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const acceptedOffers: Type[] = acceptedOffersToCheck.filter(isPresent);
    if (acceptedOffers.length > 0) {
      const acceptedOfferCollectionIdentifiers = acceptedOfferCollection.map(
        acceptedOfferItem => this.getAcceptedOfferIdentifier(acceptedOfferItem)!,
      );
      const acceptedOffersToAdd = acceptedOffers.filter(acceptedOfferItem => {
        const acceptedOfferIdentifier = this.getAcceptedOfferIdentifier(acceptedOfferItem);
        if (acceptedOfferCollectionIdentifiers.includes(acceptedOfferIdentifier)) {
          return false;
        }
        acceptedOfferCollectionIdentifiers.push(acceptedOfferIdentifier);
        return true;
      });
      return [...acceptedOffersToAdd, ...acceptedOfferCollection];
    }
    return acceptedOfferCollection;
  }

  protected convertDateFromClient<T extends IAcceptedOffer | NewAcceptedOffer | PartialUpdateAcceptedOffer>(acceptedOffer: T): RestOf<T> {
    return {
      ...acceptedOffer,
      offerDate: acceptedOffer.offerDate?.format(DATE_FORMAT) ?? null,
      offerAcceptedDate: acceptedOffer.offerAcceptedDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restAcceptedOffer: RestAcceptedOffer): IAcceptedOffer {
    return {
      ...restAcceptedOffer,
      offerDate: restAcceptedOffer.offerDate ? dayjs(restAcceptedOffer.offerDate) : undefined,
      offerAcceptedDate: restAcceptedOffer.offerAcceptedDate ? dayjs(restAcceptedOffer.offerAcceptedDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAcceptedOffer>): HttpResponse<IAcceptedOffer> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAcceptedOffer[]>): HttpResponse<IAcceptedOffer[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
