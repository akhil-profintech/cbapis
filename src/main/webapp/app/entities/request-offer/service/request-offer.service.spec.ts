import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IRequestOffer } from '../request-offer.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../request-offer.test-samples';

import { RequestOfferService, RestRequestOffer } from './request-offer.service';

const requireRestSample: RestRequestOffer = {
  ...sampleWithRequiredData,
  financeRequestDate: sampleWithRequiredData.financeRequestDate?.format(DATE_FORMAT),
};

describe('RequestOffer Service', () => {
  let service: RequestOfferService;
  let httpMock: HttpTestingController;
  let expectedResult: IRequestOffer | IRequestOffer[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RequestOfferService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a RequestOffer', () => {
      const requestOffer = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(requestOffer).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RequestOffer', () => {
      const requestOffer = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(requestOffer).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RequestOffer', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RequestOffer', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RequestOffer', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addRequestOfferToCollectionIfMissing', () => {
      it('should add a RequestOffer to an empty array', () => {
        const requestOffer: IRequestOffer = sampleWithRequiredData;
        expectedResult = service.addRequestOfferToCollectionIfMissing([], requestOffer);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(requestOffer);
      });

      it('should not add a RequestOffer to an array that contains it', () => {
        const requestOffer: IRequestOffer = sampleWithRequiredData;
        const requestOfferCollection: IRequestOffer[] = [
          {
            ...requestOffer,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRequestOfferToCollectionIfMissing(requestOfferCollection, requestOffer);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RequestOffer to an array that doesn't contain it", () => {
        const requestOffer: IRequestOffer = sampleWithRequiredData;
        const requestOfferCollection: IRequestOffer[] = [sampleWithPartialData];
        expectedResult = service.addRequestOfferToCollectionIfMissing(requestOfferCollection, requestOffer);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(requestOffer);
      });

      it('should add only unique RequestOffer to an array', () => {
        const requestOfferArray: IRequestOffer[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const requestOfferCollection: IRequestOffer[] = [sampleWithRequiredData];
        expectedResult = service.addRequestOfferToCollectionIfMissing(requestOfferCollection, ...requestOfferArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const requestOffer: IRequestOffer = sampleWithRequiredData;
        const requestOffer2: IRequestOffer = sampleWithPartialData;
        expectedResult = service.addRequestOfferToCollectionIfMissing([], requestOffer, requestOffer2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(requestOffer);
        expect(expectedResult).toContain(requestOffer2);
      });

      it('should accept null and undefined values', () => {
        const requestOffer: IRequestOffer = sampleWithRequiredData;
        expectedResult = service.addRequestOfferToCollectionIfMissing([], null, requestOffer, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(requestOffer);
      });

      it('should return initial array if no RequestOffer is added', () => {
        const requestOfferCollection: IRequestOffer[] = [sampleWithRequiredData];
        expectedResult = service.addRequestOfferToCollectionIfMissing(requestOfferCollection, undefined, null);
        expect(expectedResult).toEqual(requestOfferCollection);
      });
    });

    describe('compareRequestOffer', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRequestOffer(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareRequestOffer(entity1, entity2);
        const compareResult2 = service.compareRequestOffer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareRequestOffer(entity1, entity2);
        const compareResult2 = service.compareRequestOffer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareRequestOffer(entity1, entity2);
        const compareResult2 = service.compareRequestOffer(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
