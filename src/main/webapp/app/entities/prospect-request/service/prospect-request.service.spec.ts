import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IProspectRequest } from '../prospect-request.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../prospect-request.test-samples';

import { ProspectRequestService, RestProspectRequest } from './prospect-request.service';

const requireRestSample: RestProspectRequest = {
  ...sampleWithRequiredData,
  prospectRequestDate: sampleWithRequiredData.prospectRequestDate?.format(DATE_FORMAT),
};

describe('ProspectRequest Service', () => {
  let service: ProspectRequestService;
  let httpMock: HttpTestingController;
  let expectedResult: IProspectRequest | IProspectRequest[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ProspectRequestService);
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

    it('should create a ProspectRequest', () => {
      const prospectRequest = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(prospectRequest).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ProspectRequest', () => {
      const prospectRequest = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(prospectRequest).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ProspectRequest', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ProspectRequest', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ProspectRequest', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addProspectRequestToCollectionIfMissing', () => {
      it('should add a ProspectRequest to an empty array', () => {
        const prospectRequest: IProspectRequest = sampleWithRequiredData;
        expectedResult = service.addProspectRequestToCollectionIfMissing([], prospectRequest);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(prospectRequest);
      });

      it('should not add a ProspectRequest to an array that contains it', () => {
        const prospectRequest: IProspectRequest = sampleWithRequiredData;
        const prospectRequestCollection: IProspectRequest[] = [
          {
            ...prospectRequest,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addProspectRequestToCollectionIfMissing(prospectRequestCollection, prospectRequest);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ProspectRequest to an array that doesn't contain it", () => {
        const prospectRequest: IProspectRequest = sampleWithRequiredData;
        const prospectRequestCollection: IProspectRequest[] = [sampleWithPartialData];
        expectedResult = service.addProspectRequestToCollectionIfMissing(prospectRequestCollection, prospectRequest);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(prospectRequest);
      });

      it('should add only unique ProspectRequest to an array', () => {
        const prospectRequestArray: IProspectRequest[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const prospectRequestCollection: IProspectRequest[] = [sampleWithRequiredData];
        expectedResult = service.addProspectRequestToCollectionIfMissing(prospectRequestCollection, ...prospectRequestArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const prospectRequest: IProspectRequest = sampleWithRequiredData;
        const prospectRequest2: IProspectRequest = sampleWithPartialData;
        expectedResult = service.addProspectRequestToCollectionIfMissing([], prospectRequest, prospectRequest2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(prospectRequest);
        expect(expectedResult).toContain(prospectRequest2);
      });

      it('should accept null and undefined values', () => {
        const prospectRequest: IProspectRequest = sampleWithRequiredData;
        expectedResult = service.addProspectRequestToCollectionIfMissing([], null, prospectRequest, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(prospectRequest);
      });

      it('should return initial array if no ProspectRequest is added', () => {
        const prospectRequestCollection: IProspectRequest[] = [sampleWithRequiredData];
        expectedResult = service.addProspectRequestToCollectionIfMissing(prospectRequestCollection, undefined, null);
        expect(expectedResult).toEqual(prospectRequestCollection);
      });
    });

    describe('compareProspectRequest', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareProspectRequest(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareProspectRequest(entity1, entity2);
        const compareResult2 = service.compareProspectRequest(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareProspectRequest(entity1, entity2);
        const compareResult2 = service.compareProspectRequest(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareProspectRequest(entity1, entity2);
        const compareResult2 = service.compareProspectRequest(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
