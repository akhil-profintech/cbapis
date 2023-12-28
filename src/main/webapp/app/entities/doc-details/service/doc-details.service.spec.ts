import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDocDetails } from '../doc-details.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../doc-details.test-samples';

import { DocDetailsService } from './doc-details.service';

const requireRestSample: IDocDetails = {
  ...sampleWithRequiredData,
};

describe('DocDetails Service', () => {
  let service: DocDetailsService;
  let httpMock: HttpTestingController;
  let expectedResult: IDocDetails | IDocDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DocDetailsService);
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

    it('should create a DocDetails', () => {
      const docDetails = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(docDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DocDetails', () => {
      const docDetails = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(docDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DocDetails', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DocDetails', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a DocDetails', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDocDetailsToCollectionIfMissing', () => {
      it('should add a DocDetails to an empty array', () => {
        const docDetails: IDocDetails = sampleWithRequiredData;
        expectedResult = service.addDocDetailsToCollectionIfMissing([], docDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(docDetails);
      });

      it('should not add a DocDetails to an array that contains it', () => {
        const docDetails: IDocDetails = sampleWithRequiredData;
        const docDetailsCollection: IDocDetails[] = [
          {
            ...docDetails,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDocDetailsToCollectionIfMissing(docDetailsCollection, docDetails);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DocDetails to an array that doesn't contain it", () => {
        const docDetails: IDocDetails = sampleWithRequiredData;
        const docDetailsCollection: IDocDetails[] = [sampleWithPartialData];
        expectedResult = service.addDocDetailsToCollectionIfMissing(docDetailsCollection, docDetails);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(docDetails);
      });

      it('should add only unique DocDetails to an array', () => {
        const docDetailsArray: IDocDetails[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const docDetailsCollection: IDocDetails[] = [sampleWithRequiredData];
        expectedResult = service.addDocDetailsToCollectionIfMissing(docDetailsCollection, ...docDetailsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const docDetails: IDocDetails = sampleWithRequiredData;
        const docDetails2: IDocDetails = sampleWithPartialData;
        expectedResult = service.addDocDetailsToCollectionIfMissing([], docDetails, docDetails2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(docDetails);
        expect(expectedResult).toContain(docDetails2);
      });

      it('should accept null and undefined values', () => {
        const docDetails: IDocDetails = sampleWithRequiredData;
        expectedResult = service.addDocDetailsToCollectionIfMissing([], null, docDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(docDetails);
      });

      it('should return initial array if no DocDetails is added', () => {
        const docDetailsCollection: IDocDetails[] = [sampleWithRequiredData];
        expectedResult = service.addDocDetailsToCollectionIfMissing(docDetailsCollection, undefined, null);
        expect(expectedResult).toEqual(docDetailsCollection);
      });
    });

    describe('compareDocDetails', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDocDetails(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareDocDetails(entity1, entity2);
        const compareResult2 = service.compareDocDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareDocDetails(entity1, entity2);
        const compareResult2 = service.compareDocDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareDocDetails(entity1, entity2);
        const compareResult2 = service.compareDocDetails(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
