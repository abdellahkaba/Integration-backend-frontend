FROM node:20 as build

WORKDIR /app

COPY package*.json ./

RUN npm install --force

COPY . .

RUN npm run build --prod

FROM nginx:alpine

COPY nginx.conf /etc/nginx/nginx.conf

COPY --from=build /app/dist/demo-ui/browser /usr/share/nginx/html

EXPOSE 80